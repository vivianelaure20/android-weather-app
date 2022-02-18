package com.example.a4sure_weather_app.ui
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.data.models.WeatherX
import com.squareup.picasso.Picasso


class WeatherListViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val time: TextView = itemView.findViewById(R.id.time)
    private val temperature: TextView = itemView.findViewById(R.id.temperature)
    private val condition: ImageView = itemView.findViewById(R.id.condition)
    private val wind_speed: TextView = itemView.findViewById(R.id.wind_speed)

    fun bind(get: WeatherX) {
        temperature.text = get.main
         val url = get.icon
        Picasso.get()
            .load(url)
            .into(condition)
    }
}