//package com.example.android.darkskykotlin.database
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.android.darkskykotlin.vo.WeatherModel
//
//@Dao
//interface WeatherDatabaseDao {
//
//    @Query("SELECT * FROM currentWeatherTable")
//    fun getCurrentData(): LiveData<WeatherModel.Weather>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCurrentData(weather: WeatherModel.Weather)
//
//    @Query("DELETE FROM currentWeatherTable")
//    fun removeAllCurrentData()
//
//    @Query("SELECT * FROM dailyWeatherTable")
//    fun getAllDailyData(): LiveData<List<WeatherModel.Data>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertDailyData(weather: WeatherModel.Data)
//
//    @Query("DELETE FROM dailyWeatherTable")
//    fun removeAllDailyData()
//}