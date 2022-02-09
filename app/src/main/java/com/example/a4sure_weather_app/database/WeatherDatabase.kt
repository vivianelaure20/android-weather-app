package com.example.a4sure_weather_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a4sure_weather_app.database.WeatherDatabase.Companion.DATABASE_VERSION
import com.example.a4sure_weather_app.database.tables.CurrentEntity
import com.example.a4sure_weather_app.database.tables.DailyEntity
import com.example.a4sure_weather_app.database.tables.HourlyEntity

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
        private const val DATABASE_NAME = "weather_database"
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

        @JvmStatic
        private fun NewInstance(context: Context): WeatherDatabase {
            val instance =
                Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration().build()
            return instance
        }
    }
}

