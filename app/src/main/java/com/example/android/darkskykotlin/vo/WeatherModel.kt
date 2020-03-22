package com.example.android.darkskykotlin.vo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


object WeatherModel {

//    MODELSET 1
    data class Weather(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Data,
    val daily: Daily)

data class Daily(
    val summary: String?,
    val icon: String?,
    val data: MutableList<Data>
)

data class Data(
    val time: Long,
    val summary: String? = "",
    val icon: String? = "",
    val nearestStormDistance: Int?,
    val nearestStormBearing: Int?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val temperature: Double?,
    val apparentTemperature: Double?,
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureLow: Double?,
    val apparentTemperatureHighTime: Long?,
    val apparentTemperatureLowTime: Long?,
    val dewPoint: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windGustTime: Long?,
    val windBearing: Int?,
    val cloudCover: Double?,
    val uvIndex: Int?,
    val uvIndexTime: Long?,
    val visibility: Double?,
    val ozone: Double?
)


////    MODELSET2
//data class Weather(
//    val latitude: Double,
//    val longitude: Double,
//    val timezone: String,
//    val currently: Currently,
//    val daily: Daily
//)
//
//data class Currently(
//    val time: Int,
//    val summary: String,
//    val icon: String,
//    val nearestStormDistance: Int,
//    val precipIntensity: Double,
//    val precipIntensityError: Double,
//    val precipProbability: Double,
//    val precipType: String,
//    val temperature: Double,
//    val apparentTemperature: Double,
//    val dewPoint: Double,
//    val humidity: Double,
//    val pressure: Double,
//    val windSpeed: Double,
//    val windGust: Double,
//    val windBearing: Int,
//    val cloudCover: Double,
//    val uvIndex: Int,
//    val visibility: Double,
//    val ozone: Double
//)
//
//data class Daily(
//    val summary: String,
//    val icon: String,
//    val `data`: List<Data>
//)
//
//data class Data(
//    val time: Int,
//    val summary: String,
//    val icon: String,
//    val sunriseTime: Int,
//    val sunsetTime: Int,
//    val moonPhase: Double,
//    val precipIntensity: Double,
//    val precipIntensityMax: Double,
//    val precipIntensityMaxTime: Int,
//    val precipProbability: Double,
//    val precipType: String,
//    val temperatureHigh: Double,
//    val temperatureHighTime: Int,
//    val temperatureLow: Double,
//    val temperatureLowTime: Int,
//    val apparentTemperatureHigh: Double,
//    val apparentTemperatureHighTime: Int,
//    val apparentTemperatureLow: Double,
//    val apparentTemperatureLowTime: Int,
//    val dewPoint: Double,
//    val humidity: Double,
//    val pressure: Double,
//    val windSpeed: Double,
//    val windGust: Double,
//    val windGustTime: Int,
//    val windBearing: Int,
//    val cloudCover: Double,
//    val uvIndex: Int,
//    val uvIndexTime: Int,
//    val visibility: Int,
//    val ozone: Double,
//    val temperatureMin: Double,
//    val temperatureMinTime: Int,
//    val temperatureMax: Double,
//    val temperatureMaxTime: Int,
//    val apparentTemperatureMin: Double,
//    val apparentTemperatureMinTime: Int,
//    val apparentTemperatureMax: Double,
//    val apparentTemperatureMaxTime: Int
//)
}



