package com.example.android.darkskykotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.darkskykotlin.models.Currently
import com.example.android.darkskykotlin.models.Data
import com.example.android.darkskykotlin.models.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM currentWeatherTable")
    fun getAllCurrentData():Currently


    @Insert
    fun addCurrentData():Currently

    @Query("DELETE FROM currentWeatherTable")
    fun removeAllCurrentData()

    @Query("SELECT * FROM dailyWeatherTable")
    fun getAllDailyData(): Data

    @Insert
    fun addDailyData(): Data

    @Query("DELETE FROM dailyWeatherTable")
    fun removeAllDailyData()
}