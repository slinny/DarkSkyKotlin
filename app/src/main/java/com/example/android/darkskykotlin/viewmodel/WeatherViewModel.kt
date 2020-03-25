package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.database.WeatherDao
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.vo.Data
import com.example.android.darkskykotlin.vo.Weather
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherViewModel(
    val weatherDao: WeatherDao,
    application: Application
) : AndroidViewModel(application)
{

    val latitude = 40.7128
    val longitude = -74.0060

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val weatherApiResponseLiveData = MutableLiveData<Weather>()

    private val forecastApi by lazy { ApiService.create() }

    fun fetchForecastAtLocation() {
        forecastApi.forecast(
            BuildConfig.ApiKey,
            latitude,
            longitude)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    Timber.v("API Url call ${call.request().url()}")
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
                    weatherApiResponseLiveData.value = response.body()
                }
                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
                    Timber.e(throwable, "Error trying to fetch the user's location forecast.")
                }
            })
    }
}