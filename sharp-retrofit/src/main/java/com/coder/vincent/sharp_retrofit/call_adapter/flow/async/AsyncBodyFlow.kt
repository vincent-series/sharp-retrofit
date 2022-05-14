package com.coder.vincent.sharp_retrofit.call_adapter.flow.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AsyncBodyFlow<T>(private val call: Call<T>) : Flow<T> {
    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) {
        try {
            suspendCancellableCoroutine<T> { continuation ->
                continuation.invokeOnCancellation {
                    call.cancel()
                }
                call.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if (response.isSuccessful) {
                            continuation.resume(response.body()!!)
                        } else {
                            continuation.resumeWithException(HttpException(response))
                        }
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }.let {
                collector.emit(it)
            }
        } catch (e: Exception) {
            suspendCoroutineUninterceptedOrReturn<Nothing> { continuation ->
                Dispatchers.Default.dispatch(continuation.context) {
                    continuation.intercepted().resumeWithException(e)
                }
                COROUTINE_SUSPENDED
            }
        }
    }
}