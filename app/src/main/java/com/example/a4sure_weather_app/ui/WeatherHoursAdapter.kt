package com.example.a4sure_weather_app.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.data.models.Weather
import com.example.a4sure_weather_app.databinding.WeatherHoursListItemsBinding

class WeatherHoursAdapter(onItems: (Weather)-> Unit): RecyclerView.Adapter<WeatherHoursViewHolder>() {
    private val weatherHoursList: MutableList<Weather> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitHoursData(weatherHoursList: List<Weather>){
        if( this.weatherHoursList.isNotEmpty()){
            this.weatherHoursList.clear()
        }
        this.weatherHoursList.addAll(weatherHoursList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHoursViewHolder {
        val binding= WeatherHoursListItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return WeatherHoursViewHolder(binding)
    }

    override fun getItemCount(): Int = weatherHoursList.size

    override fun onBindViewHolder(holder: WeatherHoursViewHolder, position: Int) {
        holder.bindHours(weatherHoursList[position])
    }
}