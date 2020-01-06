package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.android.darkskykotlin.database.WeatherDao

class ViewModelFactory(
    private val dataSource: WeatherDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            return ViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}