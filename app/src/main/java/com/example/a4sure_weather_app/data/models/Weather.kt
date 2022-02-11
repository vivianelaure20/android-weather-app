package com.example.a4sure_weather_app.data.models

data class Weather(
    val clouds: Clouds,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: Main,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)