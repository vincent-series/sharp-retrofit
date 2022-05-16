package com.example.sharp_retrofit.api_service

import com.coder.vincent.sharp_retrofit.annotations.BaseUrl
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

//key:SCYrvkytJze9qyzOh
@BaseUrl("https://api.seniverse.com/v3/")
interface WeatherApiService {
    @GET("weather/now.json")
    fun getWeatherInfoNow(
        @Query("key") key: String,
        @Query("location") location: String,
    ): Flow<WeatherInfo>
}