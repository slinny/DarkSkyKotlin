package com.example.android.darkskykotlin.vo

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
    val icon: String?,
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureLow: Double?
)

//data class Data(
//    val time: Long,
//    val icon: String? = "",
//    val apparentTemperatureHigh: Double?,
//    val apparentTemperatureLow: Double?
//)

//@Entity(tableName = "daily_weather_table")
//data class Data(
//    @PrimaryKey
//    @ColumnInfo(name = "time")
//    val time: Long,
//    @ColumnInfo(name = "icon")
//    val icon: String? = "",
//    @ColumnInfo(name = "daily_high")
//    val apparentTemperatureHigh: Double?,
//    @ColumnInfo(name = "daily_low")
//    val apparentTemperatureLow: Double?
//)


