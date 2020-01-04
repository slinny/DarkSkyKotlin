package com.example.android.darkskykotlin.models


import com.google.gson.annotations.SerializedName

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Currently,
    val daily: Daily
)