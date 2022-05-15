package com.coder.vincent.sharp_retrofit.call_adapter.flow.sync

import com.coder.vincent.sharp_retrofit.call_adapter.flow.bodyFlow
import com.coder.vincent.sharp_retrofit.call_adapter.flow.responseFlow
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class FlowCallAdapter<ResponseBodyType>(
    private val responseBodyType: ResponseBodyType,
    private val withResponseWrapper: Boolean,
) : CallAdapter<ResponseBodyType, Flow<*>> {

    override fun responseType() = responseBodyType as Type

    override fun adapt(call: Call<ResponseBodyType>) =
        if (withResponseWrapper) responseFlow(call) else bodyFlow(call)
}