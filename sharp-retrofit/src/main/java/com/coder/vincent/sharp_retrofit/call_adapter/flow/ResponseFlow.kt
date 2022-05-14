package com.coder.vincent.sharp_retrofit.call_adapter.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ResponseFlow<T>(private val call: Call<T>) : Flow<Response<T>> {
    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<Response<T>>) {
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
            collector.emit(it)
        }
    }
}