package com.example.android.darkskykotlin.ui

import android.app.ActionBar
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.darkskykotlin.R
//import com.example.android.darkskykotlin.adapter.DailyAdapter
//import com.example.android.darkskykotlin.database.WeatherDatabase
import com.example.android.darkskykotlin.databinding.FragmentWeatherBinding
import com.example.android.darkskykotlin.databinding.ListItemDailyBinding
import com.example.android.darkskykotlin.networking.BASE_URL
import com.example.android.darkskykotlin.util.WeatherIcons
import com.example.android.darkskykotlin.viewmodel.WeatherViewModel
import com.example.android.darkskykotlin.vo.Data
//import com.example.android.darkskykotlin.viewmodel.WeatherViewModelFactory
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class WeatherFragment : Fragment() {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, WeatherViewModel.WeatherViewModelFactory(activity.application))
            .get(WeatherViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var dailyAdapter: DailyAdapter? = null

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.dailyWeatherList.observe(viewLifecycleOwner, Observer<List<Data>> { datas ->
            datas?.apply {
                dailyAdapter?.dailyWeatherDataList = datas
//                viewModelAdapter?.videos = videos
            }
        })
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        dailyAdapter = DailyAdapter()

        binding.root.findViewById<RecyclerView>(R.id.daily_recyclerview).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyAdapter
        }


        // Observer for the network error.
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

//    /**
//     * Helper method to generate YouTube app links
//     */
//    private val Data.launchUri: Uri
//        get() {
//            val httpUri = android.net.Uri.parse(BASE_URL)
//            return android.net.Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
//        }

//    private var weatherIconMap: Map<String, Drawable>? = null
//    private val adapter = DailyAdapter()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding = DataBindingUtil.inflate<FragmentWeatherBinding>(
//            inflater,
//            R.layout.fragment_weather, container, false
//        )

//        val application = requireNotNull(this.activity).application
//        val dataSource = WeatherDatabase.getInstance(application).weatherDao
//        val viewModelFactory = WeatherViewModelFactory(dataSource, application)
//        val weatherViewModel =
//            ViewModelProviders.of(
//                this, viewModelFactory).get(WeatherViewModel::class.java)
//
//        weatherViewModel.weatherApiResponseLiveData.observe(this, Observer { weatherModel ->
//
//            adapter.setDayForecast(weatherModel.daily.data)
//        })
//
//        weatherIconMap = WeatherIcons.map(this.context!!)
//
//        weatherViewModel.fetchForecastAtLocation()
//
//        binding.dailyRecyclerview.layoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        binding.dailyRecyclerview.adapter = adapter
//        (activity as AppCompatActivity).supportActionBar?.title = "DarkSkyWeather"

//        return binding.root
//    }

}



/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class DailyAdapter
    : RecyclerView.Adapter<DailyAdapter.ForecastViewHolder>() {

    /**
     * The videos that our Adapter will show
     */
    var dailyWeatherDataList: List<Data> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    private lateinit var weatherIcons: Map<String, Drawable>

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyAdapter.ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemDailyBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_daily, parent, false)

        // Assign weather icons using context
        weatherIcons = WeatherIcons.map(parent.context)
        return DailyAdapter.ForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyWeatherDataList.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: DailyAdapter.ForecastViewHolder, position: Int) {

        val sdf = SimpleDateFormat("EEEE")
        val dateFormat = java.util.Date((dailyWeatherDataList[position].time * 1000).toLong())
        val weekday = sdf.format(dateFormat)

        holder.binding.dayOfWeek = weekday
        holder.binding.dailyWeatherData = dailyWeatherDataList[position]
        holder.binding.dailyHigh = dailyWeatherDataList[position]
        holder.binding.dailyLow = dailyWeatherDataList[position]
        holder.binding.weatherIcon = weatherIcons[dailyWeatherDataList[position].icon]

        // Execute binding immediately inside view
        holder.binding.executePendingBindings()
    }

    fun setDayForecast(dayForecast: List<Data>?) {
        if (dayForecast == null) {
            Timber.e("dayForecast list passed in is null.")
            return
        }
        this.dailyWeatherDataList = dayForecast as MutableList<Data>
        notifyDataSetChanged()
    }

    class ForecastViewHolder(val binding: ListItemDailyBinding) : RecyclerView.ViewHolder(binding.root)

}