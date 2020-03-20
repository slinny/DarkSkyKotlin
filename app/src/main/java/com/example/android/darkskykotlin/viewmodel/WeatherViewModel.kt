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
import com.example.android.darkskykotlin.vo.WeatherModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class WeatherViewModel(application: Application) : AndroidViewModel(application){

    val darkSkyApiResponseLiveData = MutableLiveData<WeatherModel.Weather>()

    // Create the Retrofit instance
    private val forecastAdi by lazy { ApiService.create() }

    private lateinit var userLocation: Location
    private val appContext = getApplication() as Context

    private fun fetchForecastAtLocation(latitude: Double, longitude: Double) {
        forecastAdi.forecast(
            BuildConfig.ApiKey,
            latitude,
            longitude)
            .enqueue(object : Callback<WeatherModel.Weather> {
                override fun onResponse(call: Call<WeatherModel.Weather>, response: Response<WeatherModel.Weather>) {
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
                    // Pass information to the view
                    darkSkyApiResponseLiveData.value = response.body()
                }

                override fun onFailure(call: Call<WeatherModel.Weather>, throwable: Throwable) {
                }
            })
    }
}