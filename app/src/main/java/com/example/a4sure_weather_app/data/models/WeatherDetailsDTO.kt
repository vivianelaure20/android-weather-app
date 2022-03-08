package com.example.a4sure_weather_app.data.models

import java.io.Serializable

data class WeatherDetailsDTO(
    val id: Int,
    val feelsLike: Double,
    val seaLevel: Int,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val iconUrl: String,
    val description: String,
    val lat: Double,
    val long: Double,
): Serializable
