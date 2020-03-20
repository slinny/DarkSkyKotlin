package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.vo.WeatherModel
import com.google.android.gms.common.api.Api
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.darksky.net/forecast/"

interface ApiService {

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("forecast/{key}/{latitude},{longitude}")
    fun forecast(
        @Path("key") key: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Call<WeatherModel.Weather>
}