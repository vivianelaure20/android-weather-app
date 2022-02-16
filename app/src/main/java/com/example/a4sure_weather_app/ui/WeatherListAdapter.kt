package com.example.a4sure_weather_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.data.models.WeatherX

class WeatherListAdapter: RecyclerView.Adapter<WeatherListViewModel>() {

    private var weatherList= ArrayList<WeatherX>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewModel {
        return WeatherListViewModel(LayoutInflater.from(parent.context).inflate(R.layout.weather_fragment, parent,false))
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: WeatherListViewModel, position: Int) {
        holder.bind(weatherList.get(position))

    }
}