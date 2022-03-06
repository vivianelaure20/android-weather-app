package com.example.a4sure_weather_app.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.data.models.Main
import com.example.a4sure_weather_app.data.models.Weather
import com.example.a4sure_weather_app.databinding.WeatherListItemsBinding

class WeatherListAdapter(
     private val onItemsClick:(Weather)-> Unit): RecyclerView.Adapter<WeatherListViewHolder>() {
     private val weatherList: MutableList<Weather> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(weatherList: List<Weather>){
        if( this.weatherList.isNotEmpty()){
            this.weatherList.clear()
        }
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
         val binding= WeatherListItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return WeatherListViewHolder(binding,onItemsClick)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }
}
