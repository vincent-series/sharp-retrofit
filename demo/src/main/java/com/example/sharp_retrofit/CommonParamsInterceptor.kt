package com.example.sharp_retrofit

import okhttp3.Interceptor
import okhttp3.Response

class CommonParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (chain.request().url().host().equals("api.seniverse.com")) {
            val url =
                chain.request().url().newBuilder().addQueryParameter("key", "SCYrvkytJze9qyzOh")
                    .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        } else {
            chain.proceed(chain.request())
        }
    }
}