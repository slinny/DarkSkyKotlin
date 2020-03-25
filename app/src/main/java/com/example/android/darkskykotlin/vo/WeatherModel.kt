package com.example.android.darkskykotlin.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Entity(tableName = "daily_weather_table")
data class Data(
    @PrimaryKey
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "icon")
    val icon: String? = "",
    @ColumnInfo(name = "daily_high")
    val apparentTemperatureHigh: Double?,
    @ColumnInfo(name = "daily_low")
    val apparentTemperatureLow: Double?
)



