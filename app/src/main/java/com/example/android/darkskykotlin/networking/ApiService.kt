package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.darksky.net/forecast/"
const val LATITUDE = 40.7128;
const val LONTITUDE = -74.0060;
const val API_KEY = BuildConfig.ApiKey

/**
 * A retrofit service to fetch data
 */
interface ApiService {

    @GET("{key}/{latitude},{longitude}")
    fun forecast(
            @Path("key") key: String,
            @Path("latitude") latitude: Double,
            @Path("longitude") longitude: Double
    ): Deferred<WeatherContainer>
}

//returns weather but need datas

/**
 * Main entry point for network access. Call like `WeatherNetwork.apiService.forecast(API_KEY,LATITUDE,LONTITUDE)`
 */
object WeatherNetwork {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}




//
//interface ApiService {
//
//    companion object {
//        fun create(): ApiService {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
//
//    @GET("{key}/{latitude},{longitude}")
//    fun forecast(
//        @Path("key") key: String,
//        @Path("latitude") latitude: Double,
//        @Path("longitude") longitude: Double
//    ): Call<Weather>
//}