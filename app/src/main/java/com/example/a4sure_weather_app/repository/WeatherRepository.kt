package com.example.a4sure_weather_app.repository

import android.content.Context
import com.example.a4sure_weather_app.database.WeatherDatabase
import com.example.a4sure_weather_app.database.WeatherDatabaseDao
import com.example.a4sure_weather_app.database.tables.CurrentEntity
import kotlinx.coroutines.flow.Flow

class WeatherRepository(val context: Context) {
    private val weatherDao: WeatherDatabaseDao
        get() {
            return WeatherDatabase.getDatabase(context.applicationContext)!!.weatherDao
        }

    fun getCurrentForecast(cityID: Long): Flow<List<CurrentEntity>> {
        return weatherDao.getCurrentForecast(cityID)
    }

}