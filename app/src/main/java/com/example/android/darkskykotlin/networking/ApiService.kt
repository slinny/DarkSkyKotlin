package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.vo.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.darksky.net/forecast/"
const val LATITUDE = 40.7128;
const val LONTITUDE = -74.0060;

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

    @GET("{key}/{latitude},{longitude}")
    fun forecast(
        @Path("key") key: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Call<Weather>
}