package com.example.a4sure_weather_app.network


import com.example.a4sure_weather_app.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/data/2.5/forecast/daily?")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = API_KEY
    )
}



