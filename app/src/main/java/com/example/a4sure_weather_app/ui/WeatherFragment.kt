package com.example.a4sure_weather_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.databinding.WeatherFragmentBinding
import com.example.a4sure_weather_app.viewmodel.ViewModelWeather

class WeatherFragment: Fragment() {

    private var _binding: WeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelWeather by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.weather_fragment, container,false)
        val textView: TextView= view.findViewById(R.id.textView)
        val temperature: TextView= view.findViewById(R.id.temperature)

        val condition: TextView= view.findViewById(R.id.condition)

        val args = this.arguments
        val inputData = args?.get("location")
        textView.text= inputData.toString()

        return view

    }
}