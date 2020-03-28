package com.example.android.darkskykotlin.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.darkskykotlin.R
import com.example.android.darkskykotlin.adapter.DailyAdapter
import com.example.android.darkskykotlin.databinding.FragmentWeatherBinding
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import com.example.android.darkskykotlin.viewmodel.WeatherViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class WeatherFragment : Fragment() {

    private var weatherIconMap: Map<String, Drawable>? = null

    private val adapter = DailyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = WeatherViewModelFactory(application)
        val weatherViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(WeatherViewModel::class.java)

        binding.currentCity = "New York"

        weatherViewModel.darkSkyApiResponseLiveData.observe(this, Observer { weather ->
            binding.currentTemp = weather.currently

            adapter.setDayForecast(weather.daily.data)

            // Bind the current weather icon
            if (weather.currently.icon != null && weatherIconMap != null) {
                binding.currentIcon = weatherIconMap!![weather.currently.icon]
            }
        })

        weatherIconMap = WeatherIcons.map(this.context!!)

        weatherViewModel.fetchWeather()

        binding.dailyRecyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.dailyRecyclerview.adapter = adapter
        (activity as AppCompatActivity).supportActionBar?.title = "DarkSkyWeather"

        return binding.root
    }

}
