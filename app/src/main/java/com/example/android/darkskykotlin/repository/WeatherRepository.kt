package com.example.android.darkskykotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.database.DatabaseWeather
import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.database.asDomainModel
import com.example.android.darkskykotlin.networking.*
//import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.vo.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class WeatherRepository(
    private val database: WeatherDatabase
) {

    val dailyDataList: LiveData<List<Data>> = Transformations.map(database.weatherDao.getAllDailyData()) {
        it.asDomainModel()
    }

    suspend fun refreshDailyData() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh weather is called");
            val dailyDatas = WeatherNetwork.apiService.forecast(
                BuildConfig.ApiKey,
                LATITUDE,
                LONTITUDE).await()
//            val dailyDatas = weatherData.datas
            database.weatherDao.insertDailyData(dailyDatas.asDatabaseModel())
        }
    }

//???



//
//    val weatherApiResponseLiveData = MutableLiveData<Weather>()
//
//
//
//
//
//    fun fetchForecastAtLocation() {
//        WeatherNetwork.apiService.forecast(
//            BuildConfig.ApiKey,
//            LATITUDE,
//            LONTITUDE)
//            .enqueue(object : Callback<Weather> {
//                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                    Timber.v("API Url call ${call.request().url()}")
//                    if (!response.isSuccessful || response.body() == null) {
//                        return
//                    }
//                    weatherApiResponseLiveData.value = response.body()
//                }
//                override fun onFailure(call: Call<Weather>, throwable: Throwable) {
//                    Timber.e(throwable, "Error trying to fetch the user's location forecast.")
//                }
//            })
//    }

}


