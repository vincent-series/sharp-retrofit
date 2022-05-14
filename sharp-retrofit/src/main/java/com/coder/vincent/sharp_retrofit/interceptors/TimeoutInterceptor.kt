package com.coder.vincent.sharp_retrofit.interceptors

import com.coder.vincent.sharp_retrofit.annotations.CallTimeout
import com.coder.vincent.sharp_retrofit.annotations.ConnectTimeout
import com.coder.vincent.sharp_retrofit.annotations.ReadTimeout
import com.coder.vincent.sharp_retrofit.annotations.WriteTimeout
import com.coder.vincent.sharp_retrofit.bean.TimeoutSetting
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.RealInterceptorChain
import okio.AsyncTimeout
import retrofit2.Invocation
import java.lang.reflect.Method

class TimeoutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val srcRequest = chain.request()
        val realChain = chain as? RealInterceptorChain
        srcRequest.tag(Invocation::class.java)?.let { invocation ->
            val timeoutSetting = parseOverridingTimeout(invocation.method())
            timeoutSetting.call?.let {
                val callTimeout = chain.call().timeout()
                callTimeout.timeout(it.timeout.toLong(), it.timeUnit)
                if (callTimeout is AsyncTimeout) {
                    callTimeout.exit()
                    callTimeout.enter()
                }
            }

            timeoutSetting.connect?.let {
                realChain?.withConnectTimeout(it.timeout, it.timeUnit)
            }

            timeoutSetting.read?.let {
                realChain?.withReadTimeout(it.timeout, it.timeUnit)
            }

            timeoutSetting.write?.let {
                realChain?.withWriteTimeout(it.timeout, it.timeUnit)
            }

        }
        return chain.proceed(chain.request())
    }
}

private val cachedOverridingTimeouts = mutableMapOf<Method, TimeoutSetting>()
private fun parseOverridingTimeout(invokeMethod: Method): TimeoutSetting {
    cachedOverridingTimeouts[invokeMethod]?.let { return@parseOverridingTimeout it }
    synchronized(TimeoutInterceptor::class) {
        cachedOverridingTimeouts[invokeMethod]?.let { return@parseOverridingTimeout it }
        val callTimeout = invokeMethod.getAnnotation(CallTimeout::class.java)
            ?: invokeMethod.declaringClass.getAnnotation(CallTimeout::class.java)
        val connectTimeout = invokeMethod.getAnnotation(ConnectTimeout::class.java)
            ?: invokeMethod.declaringClass.getAnnotation(ConnectTimeout::class.java)
        val readTimeout = invokeMethod.getAnnotation(ReadTimeout::class.java)
            ?: invokeMethod.declaringClass.getAnnotation(ReadTimeout::class.java)
        val writeTimeout = invokeMethod.getAnnotation(WriteTimeout::class.java)
            ?: invokeMethod.declaringClass.getAnnotation(WriteTimeout::class.java)
        val timeoutSetting = TimeoutSetting(callTimeout, connectTimeout, readTimeout, writeTimeout)
        cachedOverridingTimeouts[invokeMethod] = timeoutSetting
        return@parseOverridingTimeout timeoutSetting
    }
}