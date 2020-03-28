package com.example.android.darkskykotlin.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.darkskykotlin.R
import com.example.android.darkskykotlin.databinding.ActivityMainBinding
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit  var context:Context
//    private lateinit var viewModel: WeatherViewModel
//
//    private var weatherIconMap: Map<String, Drawable>? = null
//
//    private val adapter = DailyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
//        addObservers()
//
//        weatherIconMap = WeatherIcons.map(this)
//
//        viewModel.fetchWeather()
//
//        daily_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        daily_recyclerview.adapter = adapter
    }

//    private fun addObservers() {
//            binding.currentCity = "New York"
//
//        viewModel.darkSkyApiResponseLiveData.observe(this, Observer { weather ->
//            binding.currentTemp = weather.currently
//
//            adapter.setDayForecast(weather.daily.data)
//
//            // Bind the current weather icon
//            if (weather.currently.icon != null && weatherIconMap != null) {
//                binding.currentIcon = weatherIconMap!![weather.currently.icon]
//            }
//        })
//    }
}