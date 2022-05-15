package com.coder.vincent.sharp_retrofit.call_adapter.flow

import com.coder.vincent.sharp_retrofit.call_adapter.flow.async.AsyncFlowCallAdapter
import com.coder.vincent.sharp_retrofit.call_adapter.flow.sync.FlowCallAdapter
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory private constructor(private val async: Boolean) :
    CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) return null
        if (returnType !is ParameterizedType) {
            throw IllegalStateException("the flow type must be parameterized as Flow<Foo>!")
        }

        val flowableType = getParameterUpperBound(0, returnType)
        var responseBodyType = flowableType
        val rawFlowableType = getRawType(flowableType)
        var withResponseWrapper = false

        if (rawFlowableType == Response::class.java) {
            withResponseWrapper = true
            if (flowableType !is ParameterizedType) {
                throw IllegalStateException("the response type must be parameterized as Response<Foo>!")
            } else {
                responseBodyType = getParameterUpperBound(0, flowableType)
            }
        }

        return if (async) AsyncFlowCallAdapter(
            responseBodyType,
            withResponseWrapper
        ) else FlowCallAdapter(responseBodyType, withResponseWrapper)
    }

    companion object {
        @JvmStatic
        fun create(async: Boolean = false) = FlowCallAdapterFactory(async)
    }

}