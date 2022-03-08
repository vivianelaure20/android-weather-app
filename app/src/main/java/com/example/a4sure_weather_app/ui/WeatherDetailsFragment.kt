package com.example.a4sure_weather_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.a4sure_weather_app.data.models.WeatherDetailsDTO
import com.example.a4sure_weather_app.databinding.WeatherDetailsFragmentBinding
import com.example.a4sure_weather_app.utils.Constants
import com.example.a4sure_weather_app.utils.loadImage
import com.example.a4sure_weather_app.viewmodel.DataResource
import com.example.a4sure_weather_app.viewmodel.ViewModelWeather
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WeatherDetailsFragment: Fragment() {
    private var _binding: WeatherDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerHoursAdapter: WeatherHoursAdapter
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
            val latitude= args.lat
            val longitude= args.long
            viewModel.getForecast(latitude,longitude)
        }
    }
    private fun observeHoursDataRequest(){
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherRequestStateFlow.collectLatest {
                    when (it) {
                        is DataResource.Loading -> {
                            binding.loaderHours.isVisible = false
                        }
                        is DataResource.Success -> {
                            recyclerHoursAdapter.submitHoursData(it.data.list)
                        }
                        is DataResource.Error -> {
                            Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        observeHoursDataRequest()
        setupHoursAdapter()
    }

    private fun setupHoursAdapter(){
        val recyclerHoursView = binding.recyclerHoursView
        recyclerHoursAdapter= WeatherHoursAdapter(){}
        recyclerHoursView?.adapter = recyclerHoursAdapter
    }
}