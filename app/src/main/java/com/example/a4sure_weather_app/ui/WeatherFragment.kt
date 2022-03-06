package com.example.a4sure_weather_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.data.models.Weather
import com.example.a4sure_weather_app.data.models.WeatherDetailsDTO
import com.example.a4sure_weather_app.databinding.WeatherFragmentBinding
import com.example.a4sure_weather_app.utils.Constants
import com.example.a4sure_weather_app.viewmodel.DataResource
import com.example.a4sure_weather_app.viewmodel.ViewModelWeather
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment: Fragment() {

    private var _binding: WeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: WeatherListAdapter
    private val viewModel: ViewModelWeather by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherFragmentBinding.inflate(layoutInflater, container, false)
        weatherDataForecast()
        return binding.root
    }

    private fun weatherDataForecast(){
        val args = this.arguments
        val latitudeData = args?.getDouble(Constants.LATITUDE)
        val longitudeData = args?.getDouble(Constants.LONGITUDE)
        viewModel.getForecast(latitudeData!!,longitudeData!!)
        val cityTextView = binding.city
        val countryTextView = binding.country
        val cityData = args?.get("city")
        val countryData = args?.get("country")
        cityTextView.text = cityData.toString()
        countryTextView.text = countryData.toString()
    }

    private fun observeDataRequest(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherRequestStateFlow.collectLatest {
                    when (it) {
                        is DataResource.Loading -> {
                            binding.loader.isVisible = false
                        }
                        is DataResource.Success -> {
                            recyclerAdapter.submitData(it.data.list)
//                            binding.temperatureValue.text= it.data.list[0].main.temp.toString()
//                            binding.pressureValue.text= it.data.list[0].main.pressure.toString()
//                            binding.humidityValue.text= it.data.list[0].main.humidity.toString()
                            binding.date.text= (it.data.list[0].dtTxt)
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
        observeDataRequest()
        setupAdapter()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
              showAlertDialog()
            }
        })
    }
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext()).setMessage(getString(R.string.alert_message))
            .setPositiveButton(getString(R.string.alert_positive)
            ) { _, _ ->
                (requireActivity() as MainActivity).replaceFragment(MapFragment())
            }
            .setNegativeButton(getString(R.string.alert_negative)){ _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setupAdapter(){
        val recycleView = binding.recycleView
        recyclerAdapter= WeatherListAdapter{
            val bundle = Bundle()
            bundle.putSerializable(Constants.WEATHER_DETAILS, WeatherDetailsDTO(
                it.id,
                it.main.feelsLike,
                it.main.seaLevel,
                it.main.humidity,
                it.main.pressure,
                it.main.temp,
                it.weather[0].icon,
                it.weather[0].description
            ))
            val weatherDetailsFragment = WeatherDetailsFragment()
            weatherDetailsFragment.arguments =bundle
            (requireActivity() as MainActivity).replaceFragment(weatherDetailsFragment)
        }
        recycleView?.adapter = recyclerAdapter
    }
}