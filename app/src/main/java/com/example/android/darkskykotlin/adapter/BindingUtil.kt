package com.example.android.darkskykotlin.util

import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.darkskykotlin.R
import com.example.android.darkskykotlin.vo.Currently
import com.example.android.darkskykotlin.vo.DailyData


@BindingAdapter("dailyIcon")
fun ImageView.setDailyIcon(item: DailyData) {
    setImageResource(when (item.icon) {
        "clear-day" -> R.drawable.ic_clear_day
        "clear-night" -> R.drawable.ic_clear_night
        "rain" -> R.drawable.ic_rain
        "snow" -> R.drawable.ic_snow
        "sleet" -> R.drawable.ic_sleet
        "windy" -> R.drawable.ic_windy
        "fog" -> R.drawable.ic_foggy
        "cloudy" -> R.drawable.ic_cloudy
        "partly-cloudy-day" -> R.drawable.ic_little_cloudy_day
        "partly-cloudy-night" -> R.drawable.ic_little_cloudy_night
        "thunderstorm" -> R.drawable.ic_stormy
        else -> R.drawable.ic_launcher_foreground
    })
}

@BindingAdapter("currentIcon")
fun ImageView.setCurrentIcon(item: Currently) {
    setImageResource(when (item.icon) {
        "clear-day" -> R.drawable.ic_clear_day
        "clear-night" -> R.drawable.ic_clear_night
        "rain" -> R.drawable.ic_rain
        "snow" -> R.drawable.ic_snow
        "sleet" -> R.drawable.ic_sleet
        "windy" -> R.drawable.ic_windy
        "fog" -> R.drawable.ic_foggy
        "cloudy" -> R.drawable.ic_cloudy
        "partly-cloudy-day" -> R.drawable.ic_little_cloudy_day
        "partly-cloudy-night" -> R.drawable.ic_little_cloudy_night
        "thunderstorm" -> R.drawable.ic_stormy
        else -> R.drawable.ic_launcher_background
    })
}

@BindingAdapter("date")
fun TextView.setDate(item: DailyData) {
    text = getDayOfWeek(item.time)
}

@BindingAdapter("tempHigh")
fun TextView.setTempHigh(item: DailyData) {
    text = item.temperatureHigh.toString()
}

@BindingAdapter("tempLow")
fun TextView.setTempLow(item: DailyData) {
    text = item.temperatureLow.toString()
}