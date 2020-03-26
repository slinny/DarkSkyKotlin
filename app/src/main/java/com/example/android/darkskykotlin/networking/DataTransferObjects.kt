package com.example.android.darkskykotlin.networking

import com.example.android.darkskykotlin.database.DatabaseWeather
import com.example.android.darkskykotlin.vo.Data
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherContainer(val datas: List<Data>)

@JsonClass(generateAdapter = true)
data class Data(
    val time: Long,
    val icon: String,
    val apparentTemperatureHigh: Double,
    val apparentTemperatureLow: Double)

/**
 * Convert Network results to database objects
 */
fun WeatherContainer.asDomainModel(): List<Data> {
    return datas.map {
        Data(
            time = it.time,
            icon = it.icon,
            apparentTemperatureHigh = it.apparentTemperatureHigh,
            apparentTemperatureLow = it.apparentTemperatureLow)
    }
}


/**
 * Convert Network results to database objects
 */
fun WeatherContainer.asDatabaseModel(): List<DatabaseWeather> {
    return datas.map {
        DatabaseWeather(
            time = it.time,
            icon = it.icon,
            apparentTemperatureHigh = it.apparentTemperatureHigh,
            apparentTemperatureLow = it.apparentTemperatureLow)
    }
}