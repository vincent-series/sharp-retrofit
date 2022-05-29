package com.coder.vincent.sharp_retrofit.call_adapter.flow.sync

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BodyFlowCallAdapter<R>(private val responseBodyType: R) : CallAdapter<R, Flow<R>> {
    override fun responseType() = responseBodyType as Type

    override fun adapt(call: Call<R>): Flow<R> = bodyFlow(call)
}

fun <R> bodyFlow(call: Call<R>): Flow<R> = flow {
    suspendCancellableCoroutine<R> { continuation ->
        continuation.invokeOnCancellation {
            call.cancel()
        }
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                continuation.resume(response.body()!!)
            } else {
                continuation.resumeWithException(HttpException(response))
            }
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }.let {
        emit(it)
    }
}