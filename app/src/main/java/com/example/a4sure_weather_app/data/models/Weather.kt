package com.example.a4sure_weather_app.data.models

import com.google.gson.annotations.SerializedName

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    @SerializedName("weather")
    var weather: List<WeatherX>,
    val wind: Wind
)