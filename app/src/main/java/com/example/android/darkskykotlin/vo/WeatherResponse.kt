package com.example.android.darkskykotlin.vo

data class Weather(
    val currently: Currently,
    val daily: Daily
)

data class Currently(
    val icon: String,
    val temperature: Double
)

data class Daily(
    val `data`: List<Data>
)

data class Data(
    val time: Long,
    val icon: String,
    val temperatureHigh: Double,
    val temperatureLow: Double
)