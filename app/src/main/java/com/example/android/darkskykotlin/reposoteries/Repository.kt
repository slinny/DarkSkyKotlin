package com.example.android.darkskykotlin.reposoteries

import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.models.Weather
import com.example.android.darkskykotlin.networking.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    val BASE_URL = "https://api.darksky.net/forecast/"
    val API_KEY = BuildConfig.ApiKey
    var lat:Double = 42.3601
    var lon: Double = -71.0589

    private fun retrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL + API_KEY)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    suspend fun fetchAllWeather(): Weather {
        return retrofit().fetchWeather(lat,lon)
    }
}