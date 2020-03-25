package com.example.android.darkskykotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.darkskykotlin.vo.Data

@Dao
interface WeatherDao {

    @Query("SELECT * FROM daily_weather_table")
    fun getAllDailyData(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyData(data: MutableList<Data>)

//    @Query("SELECT * FROM daily_weather_table ORDER BY time DESC LIMIT 1")
//    fun getDailyData(): Data?

    @Query("DELETE FROM daily_weather_table")
    fun removeAllDailyData()
}