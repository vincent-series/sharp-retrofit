package com.coder.vincent.sharp_retrofit.call_adapter.flow.sync

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ResponseFlowCallAdapter<T>(private val responseBodyType: T) :
    CallAdapter<T, Flow<Response<T>>> {
    override fun responseType() = responseBodyType as Type

    override fun adapt(call: Call<T>): Flow<Response<T>> = responseFlow(call)
}

fun <T> responseFlow(call: Call<T>): Flow<Response<T>> = flow {
    suspendCancellableCoroutine<Response<T>> { continuation ->
        continuation.invokeOnCancellation {
            call.cancel()
        }
        try {
            val response = call.execute()
            continuation.resume(response)
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }.let {
        emit(it)
    }
}