package com.coder.vincent.sharp_retrofit.call_adapter.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BodyFlow<T>(private val call: Call<T>) : Flow<T> {
    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) {
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
            collector.emit(it)
        }
    }
}