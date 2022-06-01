package com.example.sharp_retrofit

import com.coder.vincent.sharp_retrofit.bean.BaseUrlPlaceholder
import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory
import com.coder.vincent.sharp_retrofit.interceptors.BaseUrlInterceptor
import com.coder.vincent.sharp_retrofit.interceptors.TimeoutInterceptor
import com.example.sharp_retrofit.api_service.LunarApiService
import com.example.sharp_retrofit.api_service.WeatherApiService

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrlPlaceholder.instance)
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(BaseUrlInterceptor())
                .addInterceptor(TimeoutInterceptor())
                .addInterceptor(CommonParamsInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .build()
    val weatherApiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
    val lunarApiService: LunarApiService = retrofit.create(LunarApiService::class.java)
}