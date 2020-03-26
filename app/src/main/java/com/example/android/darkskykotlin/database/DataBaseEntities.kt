package com.example.android.darkskykotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.darkskykotlin.vo.Data

@Entity
data class DatabaseWeather constructor(
    @PrimaryKey
    val time: Long,
    val icon: String? = "",
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureLow: Double?
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseWeather>.asDomainModel(): List<Data> {
    return map {
        Data(
            time = it.time,
            icon = it.icon,
            apparentTemperatureHigh = it.apparentTemperatureHigh,
            apparentTemperatureLow = it.apparentTemperatureLow)
    }
}