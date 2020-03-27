package com.example.android.darkskykotlin.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.darkskykotlin.R
//import com.example.android.darkskykotlin.adapter.DailyAdapter
import com.example.android.darkskykotlin.databinding.ActivityMainBinding
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go
     */

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        NavigationUI.setupActionBarWithNavController(this,navController)
//        actionBar?.setTitle("DarkSkyWeather")
    }
}
