package com.example.android.darkskykotlin.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currentWeatherTable")
data class Currently(

    @PrimaryKey(autoGenerate = true)
    val currentId:Int,
    @SerializedName("time")
    val time: Int,
    val summary: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("temperature")
    val temperature: Double,
    val nearestStormDistance: Int,
    val precipIntensity: Double,
    val precipIntensityError: Double,
    val precipProbability: Double,
    val precipType: String,
    val apparentTemperature: Double,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windGust: Double,
    val windBearing: Int,
    val cloudCover: Double,
    val uvIndex: Int,
    val visibility: Double,
    val ozone: Double
)