package com.example.android.darkskykotlin.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.darkskykotlin.R
import com.example.android.darkskykotlin.adapter.DailyAdapter
import com.example.android.darkskykotlin.databinding.ActivityMainBinding
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

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

        daily_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        daily_recyclerview.adapter = adapter
    }

    private fun addObservers() {
        binding.currentCity = "New York"

        viewModel.darkSkyApiResponseLiveData.observe(this, Observer { weatherModel ->
            binding.currentTemp = weatherModel.currently

            adapter.setDayForecast(weatherModel.daily.data)

            // Bind the weather icon
            if (weatherModel.currently.icon != null && weatherIconMap != null) {
                binding.currentIcon = weatherIconMap!![weatherModel.currently.icon]
            }
        })
    }
}
