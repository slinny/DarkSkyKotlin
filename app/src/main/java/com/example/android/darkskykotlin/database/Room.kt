package com.example.android.darkskykotlin.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.Data

@Dao
interface WeatherDao {
    @Query("select * from dailyWeatherTable")
    fun getDaily(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDaily( dailyDatas: List<Data>)

    @Query("DELETE FROM dailyWeatherTable")
    fun deleteAllDailyData()

    @Query("select * from currentWeatherTable")
    fun getCurrently(): LiveData<Currently>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent( currently: Currently)

    @Query("DELETE FROM currentWeatherTable")
    fun deleteCurrentData()
}



@Database(entities = [Data::class,Currently::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val weatherDao:WeatherDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java,
                "WeatherData").build()
        }
    }
    return INSTANCE
}