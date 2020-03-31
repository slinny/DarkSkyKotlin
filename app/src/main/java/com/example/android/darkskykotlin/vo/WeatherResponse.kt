package com.example.android.darkskykotlin.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Weather(
    val currently: Currently,
    val daily: Daily
)

@Entity(tableName = "currentWeatherTable")
data class Currently(
    @PrimaryKey
    val time: Long,
    val icon: String,
    val temperature: Double
)

data class Daily(
    val `data`: List<Data>
)

@Entity(tableName = "dailyWeatherTable")
data class Data(
    @PrimaryKey
    val time: Long,
    val icon: String,
    val temperatureHigh: Double,
    val temperatureLow: Double
)