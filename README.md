# sharp-retrofit

### 功能概览

1. FlowCallAdapter，将Call对象转成Flow对象，Retrofit同Kotlin携程无缝对接，同Rxjava一致的流式编程体验
2. 可为ApiService接口或单个接口方法添加自定义注解，优雅、简便、无耦合地实现同一Retrofit实例满足不同功能配置需求，避免传统解决方式（创建多个Retrofit对象）带来的效率低下和维护不便。如
   * 同一开发环境下多base url问题，通过@BaseUrl + BaseUrlInterceptor实现
   * 不同接口指定不同超时时间问题，通过@CallTimeout、@ConnectTimeout、@ReadTimeout、@WriteTimeout注解 + TimeoutInterceptor实现
   
### 添加依赖
<pre><code>
   implementation "io.github.vincent-series:sharp-retrofit:1.8"
</code></pre>
### API DOC
完整API文档，请查阅[wiki](https://github.com/vincent-series/sharp-retrofit/wiki)
### 代码示例
#### FlowCallAdapter的使用
1. 为`Retrofit`添加`FlowCallAdapterFactory`。
```kotlin
    private val retrofit = Retrofit.Builder()
        //...
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        //...
        .build()
```
2. 声明api接口，指定返回类型为`Flow`类型，即`Flow<ResponseBody>`、`Flow<Response<ResponseBody>>`，如果使用了`GsonConverter`,还可声明为`Flow<[Bean]>`、`Flow<Response<[Bean]>>`
```kotlin
interface WeatherApiService {
   @GET("weather/now.json")
   fun getWeatherInfoNow(@Query("location") location: String): Flow<WeatherInfo>
}
```
3. 调用接口。
```kotlin
            ApiServiceManager.weatherApiService
                .getWeatherInfoNow(location = "北京")
                //transform data,if you want to
                .map { 
                    // ...
                }
                //subscribe on io dispatcher
                .flowOn(Dispatchers.IO)
                //catch errors
                .catch { ex ->
                    println("error occurs:$ex")
                }
                //subscribe data
                .collect {
                    println("weather info:$it")
                }
```
#### @BaseUrl注解的使用
1. 设置BaseUrl占位符及添加BaseUrlInterceptor拦截器。
```kotlin
    private val retrofit = Retrofit.Builder()
        //设置BaseUrl占位符
        .baseUrl(BaseUrlPlaceholder.instance)
        .client(
            OkHttpClient.Builder()
                //添加BaseUrlInterceptor拦截器
                .addInterceptor(BaseUrlInterceptor())
                //...
                .build()
        )
        //...
        .build()
```
2. 为api接口添加@BaseUrl注解。
```kotlin
@BaseUrl("https://api.seniverse.com/v3/")
interface WeatherApiService {
    @GET("weather/now.json")
    fun getWeatherInfoNow(@Query("location") location: String): Flow<WeatherInfo>
}
```
```kotlin
@BaseUrl("https://v2.alapi.cn/api/")
interface LunarApiService {
    @GET("lunar")
    fun getLunarInfo(@Query("date") date: String): Flow<LunarInfo>
}
```

   

