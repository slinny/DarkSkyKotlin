package com.example.android.darkskykotlin.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao {
    @Query("SELECT * FROM databaseweather")
    fun getAllDailyData(): LiveData<List<DatabaseWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyData(datas: List<DatabaseWeather>)

    @Query("DELETE FROM databaseweather")
    fun removeAllDailyData()
}

@Database(entities = [DatabaseWeather::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val videoDao: VideoDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java,
                "videos").build()
        }
    }
    return INSTANCE
}