package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.database.WeatherDao
import com.example.android.darkskykotlin.database.getDatabase
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.networking.LATITUDE
import com.example.android.darkskykotlin.networking.LONTITUDE
import com.example.android.darkskykotlin.networking.WeatherNetwork
import com.example.android.darkskykotlin.repository.WeatherRepository
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.Data
import com.example.android.darkskykotlin.vo.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    var darkSkyApiResponseLiveData = MutableLiveData<Weather>()

    private val weatherRepository = WeatherRepository(getDatabase(application))
//    var currentLiveData = MutableLiveData<Currently>()
//    var dailyLiveData = MutableLiveData<List<Data>>()

    fun fetchData(){
//        weatherRepository.fetchWeather()
        darkSkyApiResponseLiveData = weatherRepository.fetchWeather()
    }

//    fun getCurrent():MutableLiveData<Currently>{
////        currentLiveData.value = weatherRepository.getCurrent().value
//        currentLiveData.value = weatherRepository.darkSkyApiResponseLiveData.value?.currently
//        return currentLiveData
//    }
//
//    fun getDaily():MutableLiveData<List<Data>>{
//        dailyLiveData.value = weatherRepository.darkSkyApiResponseLiveData.value?.daily?.data
//        return dailyLiveData
//    }

//    fun cancelJob(){
//        weatherRepository.cancelJob()
//    }


//    fun fetchWeather() {
//        WeatherNetwork.apiService.forecast(
//            BuildConfig.ApiKey,
//            LATITUDE,
//            LONTITUDE)
//            .enqueue(object : Callback<Weather> {
//                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                    if (!response.isSuccessful || response.body() == null) {
//                        return
//                    }
//                    darkSkyApiResponseLiveData.value = response.body()
//                }
//
//                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
//                }
//            })
//    }
}