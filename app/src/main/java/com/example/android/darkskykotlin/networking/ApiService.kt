package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.vo.Weather
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/[latitude],[longitude]")
    suspend fun fetchAllWeather(
        @Path("latitude") lat: Double,
        @Path("longtitude") lon: Double): Weather
}