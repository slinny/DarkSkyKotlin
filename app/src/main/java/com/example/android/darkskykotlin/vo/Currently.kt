package com.example.android.darkskykotlin.vo


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currentWeatherTable")
data class Currently(

    @PrimaryKey(autoGenerate = true)
    val currentId:Int,
    @SerializedName("time")
    @ColumnInfo(name = "time")
    val time: Int,
    val summary: String,
    @SerializedName("icon")
    @ColumnInfo(name = "icon")
    val icon: String,
    @SerializedName("temperature")
    @ColumnInfo(name = "temperature")
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