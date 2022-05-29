package com.coder.vincent.sharp_retrofit.call_adapter.flow.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import java.lang.reflect.Type
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AsyncBodyFlowCallAdapter<T>(private val responseBodyType: T) : CallAdapter<T, Flow<*>> {

    override fun responseType() = responseBodyType as Type

    override fun adapt(call: Call<T>): Flow<T> = asyncBodyFlow(call)
}

fun <T> asyncBodyFlow(call: Call<T>): Flow<T> = flow {
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
            emit(it)
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



