package com.example.android.darkskykotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.DailyData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM currentWeatherTable")
    fun getAllCurrentData():Currently

    @Insert
    fun addCurrentData(currentData:Currently)

    @Query("DELETE FROM currentWeatherTable")
    fun removeAllCurrentData()

    @Query("SELECT * FROM dailyWeatherTable")
    fun getAllDailyData(): DailyData

    @Insert
    fun addDailyData(dailyData:DailyData)

    @Query("DELETE FROM dailyWeatherTable")
    fun removeAllDailyData()
}