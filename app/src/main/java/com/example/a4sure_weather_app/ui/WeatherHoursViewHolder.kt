package com.example.a4sure_weather_app.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.data.models.Weather
import com.example.a4sure_weather_app.databinding.WeatherHoursListItemsBinding
import com.example.a4sure_weather_app.utils.loadImage

class WeatherHoursViewHolder(private val binding: WeatherHoursListItemsBinding): RecyclerView.ViewHolder(binding.root){

    private var time = binding.time
    private var iconHours = binding.iconHours
    private var temp= binding.temp

    fun bindHours(get: Weather) {
        time.text = get.dtTxt
        var url = get.weather[0].icon
        iconHours.loadImage(url)
        temp.text = get.main.temp.toString()
    }
}
