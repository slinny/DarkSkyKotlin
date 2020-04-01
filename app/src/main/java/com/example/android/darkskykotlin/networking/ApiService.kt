package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.vo.Weather
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.darksky.net/forecast/"
const val LATITUDE = 40.7128;
const val LONTITUDE = -74.0060;


interface ApiService {
    @GET("{key}/{latitude},{longitude}")
    fun forecast(
        @Path("key") key: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ):
//            Deferred<Weather>
            Call<Weather>
}

object WeatherNetwork {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

    val apiService = retrofit.create(ApiService::class.java)

}