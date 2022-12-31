package com.example.sharp_retrofit


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            ApiServiceManager.lunarApiService
                .getLunarInfo(token = "LwExDtUWhF3rH5ib", date = "2022-05-16")
                .flowOn(Dispatchers.IO)
                .catch {
                    println(it)
                }
                .collect {
                    println(it)
                }
        }

//        MainScope().launch {
//            ApiServiceManager.weatherApiService
//                .getWeatherInfoNow(location = "北京")
//                //transform data,if you want to
//                .map {
//                    // ...
//                }
//                //subscribe on io dispatcher
//                .flowOn(Dispatchers.IO)
//                //catch errors
//                .catch { ex ->
//                    println("error occurs:$ex")
//                }
//                //subscribe data
//                .collect {
//                    println("weather info:$it")
//                }
//        }
    }
}