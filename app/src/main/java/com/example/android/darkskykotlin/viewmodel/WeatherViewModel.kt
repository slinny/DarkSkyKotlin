package com.example.android.darkskykotlin.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.networking.LATITUDE
import com.example.android.darkskykotlin.networking.LONTITUDE
import com.example.android.darkskykotlin.vo.WeatherModel
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val darkSkyApiResponseLiveData = MutableLiveData<WeatherModel.Weather>()

    private val forecastAdi by lazy { ApiService.create() }

    private val appContext = getApplication() as Context

    fun fetchWeather() {
        forecastAdi.forecast(
            BuildConfig.ApiKey,
            LATITUDE,
            LONTITUDE)
            .enqueue(object : Callback<WeatherModel.Weather> {
                override fun onResponse(call: Call<WeatherModel.Weather>, response: Response<WeatherModel.Weather>) {
                    Timber.v("API Url call ${call.request().url()}")
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
                    // Pass information to the view
                    darkSkyApiResponseLiveData.value = response.body()
                }

                override fun onFailure(call: Call<WeatherModel.Weather>, throwable: Throwable) {
                    Timber.e(throwable, "Error trying to fetch the user's location forecast.")
                }
            })
    }
}