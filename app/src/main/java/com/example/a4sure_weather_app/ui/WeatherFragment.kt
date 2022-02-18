package com.example.a4sure_weather_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.databinding.WeatherFragmentBinding
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
        val textView: TextView = binding.root?.findViewById(R.id.textView)
        val args = this.arguments
        val inputData = args?.get("location")
        textView.text = inputData.toString()

        return binding.root
    }


    private fun observeDataRequest(view: View){
        val loader = view?.findViewById<ProgressBar>(R.id.loader)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherRequestStateFlow.collectLatest {
                    when (it) {
                        is DataResource.Loading -> {
                            loader.visibility = View.VISIBLE

                        }
                        is DataResource.Success -> {
                            //make a loader here
                            recyclerAdapter.submitData(it.data.list[0].weather)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeDataRequest(view)
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
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, MapFragment())
                    ?.commit()
            }
            .setNegativeButton(getString(R.string.alert_negative)){ _, _ -> }

        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun setupAdapter(){
        val recycleView = view?.findViewById<RecyclerView>(R.id.recycleView)
        recyclerAdapter = WeatherListAdapter(){}
        recycleView?.adapter = recyclerAdapter
    }
}