package com.example.a4sure_weather_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.data.models.WeatherDetailsDTO
import com.example.a4sure_weather_app.databinding.WeatherDetailsFragmentBinding
import com.example.a4sure_weather_app.utils.Constants
import com.example.a4sure_weather_app.utils.loadImage
import com.example.a4sure_weather_app.viewmodel.ViewModelWeather
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment: Fragment() {
    private var _binding: WeatherDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: WeatherListAdapter
    private val viewModel: ViewModelWeather by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherDetailsFragmentBinding.inflate(layoutInflater, container, false)
        weatherDetails()
        return binding.root
    }

    private fun weatherDetails(){
        arguments?.let{
            val args = it.getSerializable(Constants.WEATHER_DETAILS) as WeatherDetailsDTO
            binding.temperature.text = args.temp.toString()
            binding.icon.loadImage(args.iconUrl)
            binding.feelsLike.text = args.feelsLike.toString()
            binding.pressure.text = args.pressure.toString()
            binding.description.text = args.description.toString()
            binding.seaLevels.text = args.seaLevel.toString()
            binding.humidity.text = args.humidity.toString()
        }
    }

}