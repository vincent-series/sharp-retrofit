package com.example.sharp_retrofit


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            ApiServiceManager.weatherApiService
                .getWeatherInfoInThreeDays(key = "SCYrvkytJze9qyzOh", location = "北京")
                .flowOn(Dispatchers.IO)
                .catch { ex: Throwable ->
                    println(ex)
                }
                .collect {
                    println("*******************$it*******************")
                }
        }
    }
}