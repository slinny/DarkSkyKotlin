package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.database.getDatabase
import com.example.android.darkskykotlin.repository.WeatherRepository
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.Data

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository(getDatabase(application))
    var currentLiveData = MutableLiveData<Currently>()
    var dailyLiveData = MutableLiveData<List<Data>>()

    fun fetchData(){
        weatherRepository.fetchWeather()
        currentLiveData = weatherRepository.currentLiveData
        dailyLiveData = weatherRepository.dailyLiveData
//        currentLiveData = weatherRepository.getCurrent()
//        dailyLiveData = weatherRepository.getDaily()
    }

//    fun cancelJob(){
//        weatherRepository.cancelJob()
//    }
}