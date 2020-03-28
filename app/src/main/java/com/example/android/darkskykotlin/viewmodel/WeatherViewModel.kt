package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.networking.LATITUDE
import com.example.android.darkskykotlin.networking.LONTITUDE
import com.example.android.darkskykotlin.vo.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val darkSkyApiResponseLiveData = MutableLiveData<Weather>()

    private val forecastAdi by lazy { ApiService.create() }

    private val appContext = getApplication() as Context

    fun fetchWeather() {
        forecastAdi.forecast(
            BuildConfig.ApiKey,
            LATITUDE,
            LONTITUDE)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
                    darkSkyApiResponseLiveData.value = response.body()
                }

                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
                }
            })
    }
}