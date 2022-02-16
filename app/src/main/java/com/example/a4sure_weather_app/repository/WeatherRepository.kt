package com.example.a4sure_weather_app.repository

import com.example.a4sure_weather_app.data.models.WeatherResponse
import com.example.a4sure_weather_app.network.ApiService
import com.example.a4sure_weather_app.viewmodel.DataResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
    ) {
    suspend fun getForecast(lat: Double, long: Double): Flow<DataResource<WeatherResponse>> = flow {
        try{
            val response = apiService.getWeather(lat, long)
            emit(DataResource.Success(response))
        }catch (exception: Exception){
            emit(DataResource.Error(exception,exception.message?:"Sorry, something went wrong, please try again"))
        }
    }
}

