package com.example.android.darkskykotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.darkskykotlin.BuildConfig
//import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.vo.Data
import com.example.android.darkskykotlin.vo.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherRepository(
//    private val database: WeatherDatabase
) {

//    val videos: LiveData<List<Data>> = Transformations.map(database.weatherDao.getAllDailyData()) {
//        it.asDomainModel()
//    }

    val latitude = 40.7128
    val longitude = -74.0060

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