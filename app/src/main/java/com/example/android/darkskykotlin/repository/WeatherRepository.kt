package com.example.android.darkskykotlin.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
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

class WeatherRepository (
    private val weatherDatabase: WeatherDatabase
){

    var currentLiveData = MutableLiveData<Currently>()
    var dailyLiveData = MutableLiveData<List<Data>>()

//    private var repoJob = Job()
//    private val uiScopeIO = CoroutineScope(Dispatchers.IO + repoJob)
//    private val uiScopeM = CoroutineScope(Dispatchers.Main + repoJob)

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
                    currentLiveData.value = response.body()!!.currently
                    dailyLiveData.value = response.body()!!.daily.data
//                    uiScopeIO.launch {
//                        weatherDatabase.weatherDao.deleteCurrentData()
//                        weatherDatabase.weatherDao.deleteAllDailyData()
//                        weatherDatabase.weatherDao.insertCurrent(response.body()!!.currently)
//                        weatherDatabase.weatherDao.insertAllDaily(response.body()!!.daily.data)
//                    }
                }

                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
                }
            })
    }

//    fun getCurrent():MutableLiveData<Currently>{
//        uiScopeM.launch {
//            currentLiveData = weatherDatabase.weatherDao.getCurrently().value as MutableLiveData<Currently>
//        }
//        return currentLiveData
//    }
//
//    fun getDaily():MutableLiveData<List<Data>>{
//        uiScopeM.launch {
//            dailyLiveData = weatherDatabase.weatherDao.getDaily().value as MutableLiveData<List<Data>>
//        }
//        return dailyLiveData
//    }
//
//    fun cancelJob() {
//        repoJob.cancel()
//    }
}