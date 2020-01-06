package com.example.android.darkskykotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.darkskykotlin.database.WeatherDao

class ViewModel(val database:WeatherDao, application: Application) :AndroidViewModel(application){

}