package com.example.a4sure_weather_app.network

import com.example.a4sure_weather_app.data.models.WeatherResponse
import com.example.a4sure_weather_app.utils.Constants.API_KEY
import com.example.a4sure_weather_app.utils.Constants.UNITS
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/forecast?")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = API_KEY,
        @Query("units") units: String = UNITS
    ): WeatherResponse
}



