package com.example.android.darkskykotlin.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.R
import com.example.android.darkskykotlin.adapter.DailyAdapter
import com.example.android.darkskykotlin.databinding.ActivityMainBinding
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.networking.LATITUDE
import com.example.android.darkskykotlin.networking.LONTITUDE
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import com.example.android.darkskykotlin.vo.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

const val REQUEST_COARSE_LOCATION = 5678

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherViewModel

    private var weatherIconMap: Map<String, Drawable>? = null

    private val adapter = DailyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        addObservers()

        weatherIconMap = WeatherIcons.map(this)

        viewModel.getUsersCurrentLocation()

        daily_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        daily_recyclerview.adapter = adapter
    }

    private fun addObservers() {
        viewModel.requestLocationPermissionLiveData.observe(this, Observer { shouldRequestPermission ->
            if (shouldRequestPermission) {
                Timber.v("requesting permission")
                requestPermissions()
            }
        })

        viewModel.locationNameLiveData.observe(this, Observer { locationName ->
            binding.currentCity = locationName
        })

        viewModel.darkSkyApiResponseLiveData.observe(this, Observer { darkSkyModel ->
            binding.currentTemp = darkSkyModel.currently

            adapter.setDayForecast(darkSkyModel.daily.data)

            // Bind the current weather icon
            if (darkSkyModel.currently.icon != null && weatherIconMap != null) {
                binding.currentIcon = weatherIconMap!![darkSkyModel.currently.icon]
            }
        })
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_COARSE_LOCATION && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Timber.v("User gave location permission, continue with getting user's last location.")
            viewModel.getUsersCurrentLocation()
        } else {
            Timber.v("User refused to give location permission. Continue using the default location.")
        }
    }
}