package com.example.android.darkskykotlin.vo

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Currently,
    val daily: Daily
)