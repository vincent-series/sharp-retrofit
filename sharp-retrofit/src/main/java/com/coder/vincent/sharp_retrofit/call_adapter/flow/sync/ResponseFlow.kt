package com.coder.vincent.sharp_retrofit.call_adapter.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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