package com.example.a4sure_weather_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a4sure_weather_app.database.WeatherDatabase.Companion.DATABASE_VERSION
import com.example.a4sure_weather_app.database.tables.CurrentEntity
import com.example.a4sure_weather_app.database.tables.DailyEntity
import com.example.a4sure_weather_app.database.tables.HourlyEntity
import com.example.a4sure_weather_app.di.modules.AppModule.NewInstance

@Database(version = DATABASE_VERSION, entities = [
        CurrentEntity::class,
        HourlyEntity::class,
        DailyEntity::class,
    ],
    exportSchema = false
)

abstract class WeatherDatabase: RoomDatabase() {
    abstract val weatherDao: WeatherDatabaseDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "weather_database"
        private var instance: WeatherDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): WeatherDatabase? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = NewInstance(context)
                    }
                }
            }
            return instance as WeatherDatabase
        }

    }
}

