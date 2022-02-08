package com.example.a4sure_weather_app.ui

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a4sure_weather_app.R
import com.example.a4sure_weather_app.databinding.MapFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MapFragment : Fragment() {
    private var _binding: MapFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MapFragmentBinding.inflate(layoutInflater, container, false)
        setupMap()
        return binding.root
    }
    private fun setupMap(){
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        supportMapFragment.getMapAsync { googleMap ->
            googleMap.setOnMapClickListener {
                Log.d("MapFragment", "onCreateView: lat long: $it")
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val address = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                Log.d("MapFragment", "onCreateView: address: $address")
                Log.d("MapFragment", "onCreateView: address line: ${address[0].getAddressLine(0)}")
                Log.d("MapFragment", "onCreateView: locality: ${address[0].locality}")
                Log.d("MapFragment", "onCreateView: Country name: ${address[0].countryName}")
                Log.d("MapFragment", "onCreateView: Admin Area: ${address[0].adminArea}")
                val markerOptions = MarkerOptions()
                markerOptions.position(it)
                markerOptions.title("Lat: ${it.latitude} - Long: ${it.longitude}")
                markerOptions.snippet(address[0].locality)
                googleMap.clear()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 7f))
                googleMap.addMarker(markerOptions)
            }
        }
    }
}


