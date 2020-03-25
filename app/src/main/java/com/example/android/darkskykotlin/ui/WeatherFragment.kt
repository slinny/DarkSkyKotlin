package com.example.android.darkskykotlin.ui

import android.app.ActionBar
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
import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.databinding.FragmentWeatherBinding
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import com.example.android.darkskykotlin.viewmodel.WeatherViewModelFactory
import kotlinx.android.synthetic.main.fragment_weather.*

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
        val binding = DataBindingUtil.inflate<FragmentWeatherBinding>(
            inflater,
            R.layout.fragment_weather, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application).weatherDao
        val viewModelFactory = WeatherViewModelFactory(dataSource, application)
        val weatherViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(WeatherViewModel::class.java)

        weatherViewModel.weatherApiResponseLiveData.observe(this, Observer { weatherModel ->

            adapter.setDayForecast(weatherModel.daily.data)
        })

        weatherIconMap = WeatherIcons.map(this.context!!)

        weatherViewModel.getUsersCurrentLocation()

        binding.dailyRecyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.dailyRecyclerview.adapter = adapter
        (activity as AppCompatActivity).supportActionBar?.title = "DarkSkyWeather"

        return binding.root
    }

}
