package com.example.a4sure_weather_app.database

import androidx.room.Dao
import androidx.room.Query
import com.example.a4sure_weather_app.database.tables.CurrentEntity
import com.example.a4sure_weather_app.database.tables.DailyEntity
import com.example.a4sure_weather_app.database.tables.HourlyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDatabaseDao {
    @Query(
    "SELECT * FROM current_table WHERE cityId = :cityID "
    )
    fun getCurrentForecast(): Flow<CurrentEntity?>

    @Query(
        "SELECT * FROM hourly_table WHERE cityId = :cityID"
    )
    fun getHourlyForecast(): Flow<List<HourlyEntity>>

    @Query(
       "SELECT * FROM daily_table WHERE cityId = :cityID"
    )
    fun getDailyForecast(): Flow<List<DailyEntity>>
}