package com.coder.vincent.sharp_retrofit.call_adapter.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun <T> bodyFlow(call: Call<T>): Flow<T> = flow {
    suspendCancellableCoroutine<T> { continuation ->
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