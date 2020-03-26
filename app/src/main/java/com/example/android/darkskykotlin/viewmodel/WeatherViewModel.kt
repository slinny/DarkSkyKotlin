package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.darkskykotlin.database.getDatabase
import com.example.android.darkskykotlin.repository.WeatherRepository
import kotlinx.coroutines.*
import java.io.IOException

class WeatherViewModel(
    application: Application
) : AndroidViewModel(application)
{

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val weatherRepository = WeatherRepository(getDatabase(application))

    /**
     * A playlist of dailyWeatherData displayed on the screen.
     */
    val dailyWeatherList = weatherRepository.dailyDataList

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val weatherModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(weatherModelJob + Dispatchers.Main)

    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                weatherRepository.refreshDailyData()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(dailyWeatherList.value!!.isEmpty())
                    _eventNetworkError.value = true
            }
        }
    }


    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        weatherModelJob.cancel()
    }

    /**
     * Factory for constructing ViewModel with parameter
     */
    class WeatherViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

//    private val weatherResponse = WeatherRepository(WeatherDatabase.getInstance(application))
//
//    val dailyWeatherList = WeatherRepository.

//    val latitude = 40.7128
//    val longitude = -74.0060
//
//    val weatherApiResponseLiveData = MutableLiveData<Weather>()
//
//    private val forecastApi by lazy { ApiService.create() }
//
//    fun fetchForecastAtLocation() {
//        forecastApi.forecast(
//            BuildConfig.ApiKey,
//            latitude,
//            longitude)
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