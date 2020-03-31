package com.example.android.darkskykotlin.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.database.WeatherDao
import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.networking.LATITUDE
import com.example.android.darkskykotlin.networking.LONTITUDE
import com.example.android.darkskykotlin.networking.WeatherNetwork
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.Data
import com.example.android.darkskykotlin.vo.Weather
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository (private val weatherDatabase: WeatherDatabase){

//    val darkSkyApiResponseLiveData = MutableLiveData<Weather>()
    val currentLiveData = MutableLiveData<Currently>()
    val dailyLiveData = MutableLiveData<List<Data>>()

//    private var repoJob = Job()
//    private val uiScope = CoroutineScope(Dispatchers.IO + repoJob)

//    fun fetchWeather(): MutableLiveData<Weather>
    fun fetchWeather(){
        WeatherNetwork.apiService.forecast(
            BuildConfig.ApiKey,
            LATITUDE,
            LONTITUDE)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
//                    darkSkyApiResponseLiveData.value = response.body()
                    currentLiveData.value = response.body()!!.currently
                    dailyLiveData.value = response.body()!!.daily.data
//                    uiScope.launch {
//                        weatherDatabase.weatherDao.deleteCurrentData()
//                        weatherDatabase.weatherDao.deleteAllDailyData()
//                        weatherDatabase.weatherDao.insertCurrent(response.body()!!.currently)
//                        weatherDatabase.weatherDao.insertAllDaily(response.body()!!.daily.data)
//                    }
                }

                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
                }
            })
//        return darkSkyApiResponseLiveData
    }

//    fun getCurrent():MutableLiveData<Currently?>{
//        uiScope.launch {
//            fetchWeather()
//            currentLiveData.value = weatherDatabase.weatherDao.getCurrently().value
//        }
//        repoJob.cancel()
//        return currentLiveData
//    }

//    fun getDaily():MutableLiveData<List<Data>>{
//        uiScope.launch {
//            fetchWeather()
////            dailyLiveData.value = weatherDatabase.weatherDao.getDaily().value
//        }
////        repoJob.cancel()
//        return dailyLiveData
//    }

//    fun cancelJob(){
//        repoJob.cancel()
//    }



//    init {
//        initializeWeather()
//    }
//
//    private fun initializeWeather() {
//        uiScope.launch {
//            currentLiveData.value = getCurrentFromDatabase().value
//            dailyLiveData.value = getDailyFromDatabase().value as Data?
//        }
//    }
//
//    private suspend fun getCurrentFromDatabase(): LiveData<Currently> {
//        return withContext(Dispatchers.IO) {
//            var current = weatherDao.getCurrently()
//            current
//        }
//    }
//
//    private suspend fun getDailyFromDatabase(): LiveData<List<Data>> {
//        return withContext(Dispatchers.IO) {
//            var datas = weatherDao.getDaily()
//            datas
//        }
//    }
}