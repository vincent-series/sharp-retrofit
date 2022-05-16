package com.example.sharp_retrofit.api_service

import com.coder.vincent.sharp_retrofit.annotations.BaseUrl
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

@BaseUrl("https://v2.alapi.cn/api/")
interface LunarApiService {
    @GET("lunar")
    fun getLunarInfo(@Query("token") token: String, @Query("date") date: String): Flow<LunarInfo>
}