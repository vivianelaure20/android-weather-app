package com.example.a4sure_weather_app.data.models

data class WeatherResponse (
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<Weather>
)