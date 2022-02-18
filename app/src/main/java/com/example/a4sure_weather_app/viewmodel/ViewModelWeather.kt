package com.example.a4sure_weather_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a4sure_weather_app.data.models.WeatherResponse
import com.example.a4sure_weather_app.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelWeather @Inject constructor(
    private val repository: WeatherRepository
    ) : ViewModel() {
        private val _weatherRequestMutableStateFlow = MutableStateFlow<DataResource<WeatherResponse>>(DataResource.None)
         val weatherRequestStateFlow = _weatherRequestMutableStateFlow.asStateFlow()

    fun getForecast(latitude: Double,longitude: Double) = viewModelScope.launch{
        _weatherRequestMutableStateFlow.value = DataResource.Loading
        repository.getForecast(latitude,longitude).collect {
            _weatherRequestMutableStateFlow.value= it
        }
    }
}

sealed class DataResource<out R>{
    object None: DataResource<Nothing>()
    object Loading: DataResource<Nothing>()
    data class Success<out T>(val data: T): DataResource<T>()
    data class Error(val exception: Exception, val message: String): DataResource<Nothing>()
}