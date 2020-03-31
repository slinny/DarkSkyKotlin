//package com.example.android.darkskykotlin.adapter
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.example.android.darkskykotlin.vo.Currently
//import com.example.android.darkskykotlin.vo.Daily
//import com.example.android.darkskykotlin.vo.Weather
//
//@Entity
//data class DatabaseWeather constructor(
//    @PrimaryKey(autoGenerate = true)
//    var weatherId: Long = 0L,
//    val currently: Currently,
//    val daily: Daily
//)
//
///**
// * Map Database to domain entities
// */
//fun DatabaseWeather.asDomainModel(): Weather {
//    return map {
//        Weather(
//            currently
//
//
//            url = it.url,
//            title = it.title,
//            description = it.description,
//            updated = it.updated,
//            thumbnail = it.thumbnail)
//    }
//}