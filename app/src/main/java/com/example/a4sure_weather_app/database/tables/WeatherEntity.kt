package com.example.a4sure_weather_app.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_table")
data class CurrentEntity(
    @PrimaryKey(autoGenerate = false)
    val cityID: Long,
    val date: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Int,
    val feelsLike: Int,
    val pressure: Double,
    val humidity: Int,
    val clouds: Int,
    val uvi: Int,
    val visibility: Int,
    val windSpeed: Int,
    val windGust: Int,
    val windDeg: Int,
    val icon: String,
    val description: String
)

@Entity(tableName = "hourly_table", primaryKeys = ["cityID", "date"])
data class HourlyEntity(
    val cityID: Long,
    val date: Long,
    val temp: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val windGust: Int,
    val windDeg: Int,
    val rain: Double?,
    val snow: Double?,
    val icon: String,
    val description: String
)

@Entity(tableName = "daily_table", primaryKeys = ["cityID", "date"])
data class DailyEntity(
    val cityID: Long,
    val date: Long,
    val tempMin: Int,
    val tempMax: Int,
    val tempMorn: Int,
    val tempDay: Int,
    val tempEve: Int,
    val tempNight: Int,
    val feelsLikeMorn: Int?,
    val feelsLikeDay: Int?,
    val feelsLikeEve: Int?,
    val feelsLikeNight: Int?,
    val humidity: Int,
    val windSpeed: Int,
    val windGust: Int,
    val windDeg: Int,
    val pop: Int,
    val rain: Double?,
    val snow: Double?,
    val icon: String,
    val description: String
)
