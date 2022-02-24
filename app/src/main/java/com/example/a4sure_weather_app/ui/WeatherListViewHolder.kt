package com.example.a4sure_weather_app.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.data.models.Weather
import com.example.a4sure_weather_app.databinding.WeatherListItemsBinding
import com.squareup.picasso.Picasso

class WeatherListViewHolder(private val binding: WeatherListItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    private val main = binding.main
    private val icon = binding.icon
    private val description= binding.description

    fun bind(get: Weather) {
        main.text = get.weather[0].main
         val url = get.weather[0].icon
        Picasso.get()
            .load("http://openweathermap.org/img/wn/$url@2x.png")
            .into(icon)
         description.text = get.weather[0].description
    }

}