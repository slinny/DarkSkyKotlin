package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {
    @GET("/[latitude],[longitude]")
    suspend fun fetchWeather(
        @Path("latitude") lat: Double,
        @Path("longtitude") lon: Double): Weather
}