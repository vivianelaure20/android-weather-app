package com.example.a4sure_weather_app.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String){
    Picasso.get()
        .load("http://openweathermap.org/img/wn/$url@2x.png")
        .into(this)
}