package com.example.android.darkskykotlin

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.android.darkskykotlin.database.WeatherDao
import com.example.android.darkskykotlin.database.WeatherDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DataBaseTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: WeatherDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        weatherDao = db.weatherDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWeatherData() {
//        val dailyItem = WeatherModel().daily.data
//        weatherDao.addDailyData(dailyItem)
        val dailyHigh = weatherDao.getAllDailyData().getValue()?.get(0)?.apparentTemperatureHigh
        Assert.assertEquals(dailyHigh, null)
    }

}