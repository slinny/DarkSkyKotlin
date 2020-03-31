//package com.example.android.darkskykotlin.networking
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.example.android.darkskykotlin.vo.Currently
//import com.example.android.darkskykotlin.vo.Daily
//import com.example.android.darkskykotlin.vo.Weather
//import com.squareup.moshi.JsonClass
//
//@JsonClass(generateAdapter = true)
//data class WeatherContainer(val weather: Weather)
//
//@JsonClass(generateAdapter = true)
//data class Weather(
//    @PrimaryKey(autoGenerate = true)
//    var weatherId: Long = 0L,
//    val currently: Currently,
//    val daily: Daily
//)
//
///**
// * Convert Network results to database objects
// */
//fun WeatherContainer.asDomainModel(): Weather {
//    return weather.map {
//        Weather(
//            weatherId = it.
//            description = it.description,
//            url = it.url,
//            updated = it.updated,
//            thumbnail = it.thumbnail)
//    }
//}
//
//
///**
// * Convert Network results to database objects
// */
//fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo> {
//    return videos.map {
//        DatabaseVideo(
//            title = it.title,
//            description = it.description,
//            url = it.url,
//            updated = it.updated,
//            thumbnail = it.thumbnail)
//    }
//}