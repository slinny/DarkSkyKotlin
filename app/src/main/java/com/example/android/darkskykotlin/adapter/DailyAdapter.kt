//package com.example.android.darkskykotlin.adapter
//
//import android.graphics.drawable.Drawable
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.android.darkskykotlin.R
//import com.example.android.darkskykotlin.databinding.ListItemDailyBinding
//import com.example.android.darkskykotlin.util.WeatherIcons
//import com.example.android.darkskykotlin.vo.Data
//import timber.log.Timber
//import java.text.SimpleDateFormat
//
//
//class DailyAdapter
//    : RecyclerView.Adapter<DailyAdapter.ForecastViewHolder>() {
//
//    private var dailyWeatherDataList: MutableList<Data> = mutableListOf()
//    private lateinit var weatherIcons: Map<String, Drawable>
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding: ListItemDailyBinding
//                = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_daily, parent, false)
//
//        // Assign weather icons using context
//        weatherIcons = WeatherIcons.map(parent.context)
//        return ForecastViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return dailyWeatherDataList.size
//    }
//
//    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
//
//        val sdf = SimpleDateFormat("EEEE")
//        val dateFormat = java.util.Date((dailyWeatherDataList[position].time * 1000).toLong())
//        val weekday = sdf.format(dateFormat)
//
//        holder.binding.dayOfWeek = weekday
//        holder.binding.dailyWeatherData = dailyWeatherDataList[position]
//        holder.binding.dailyHigh = dailyWeatherDataList[position]
//        holder.binding.dailyLow = dailyWeatherDataList[position]
//        holder.binding.weatherIcon = weatherIcons[dailyWeatherDataList[position].icon]
//
//        // Execute binding immediately inside view
//        holder.binding.executePendingBindings()
//    }
//
//    fun setDayForecast(dayForecast: List<Data>?) {
//        if (dayForecast == null) {
//            Timber.e("dayForecast list passed in is null.")
//            return
//        }
//        this.dailyWeatherDataList = dayForecast as MutableList<Data>
//        notifyDataSetChanged()
//    }
//
//    class ForecastViewHolder(val binding: ListItemDailyBinding) : RecyclerView.ViewHolder(binding.root)
//}