package com.example.android.darkskykotlin.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.darkskykotlin.BuildConfig
import com.example.android.darkskykotlin.networking.ApiService
import com.example.android.darkskykotlin.vo.WeatherModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.mapbox.api.geocoding.v5.GeocodingCriteria
import com.mapbox.api.geocoding.v5.MapboxGeocoding
import com.mapbox.api.geocoding.v5.models.CarmenFeature
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.plugins.places.autocomplete.ui.PlaceSelectionListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

const val DEFAULT_LOCATION_NAME = "New York"
const val MAPBOX_ACCESS_TOKEN = "pk.eyJ1Ijoic2lyYW5saSIsImEiOiJjazd6and6OG8wNnN5M2VzYmRhOTVxYWh3In0.AdY_pKX83wDCRcuvYjm7Hw"
@JvmField
val DEFAULT_LOCATION = Location("default").apply {
    latitude = 40.7128
    longitude = -74.0060
}

class WeatherViewModel(application: Application) : AndroidViewModel(application),
    OnSuccessListener<Location>, OnFailureListener, Callback<GeocodingResponse>,
    PlaceSelectionListener {

    val requestLocationPermissionLiveData = MutableLiveData<Boolean>()
    val weatherApiResponseLiveData = MutableLiveData<WeatherModel.Weather>()
    val userFinishedSearchLiveData = MutableLiveData<Boolean>()
    val locationNameLiveData = MutableLiveData<String>()

    private val forecastAdi by lazy { ApiService.create() }

    private var userLocation: Location = DEFAULT_LOCATION
    private val appContext = getApplication() as Context

    private fun fetchForecastAtLocation(latitude: Double, longitude: Double) {
        forecastAdi.forecast(
            BuildConfig.ApiKey,
            latitude,
            longitude,
            "us",
            arrayListOf("minutely", "hourly", "alerts", "flags").toString())
            .enqueue(object : Callback<WeatherModel.Weather> {
                override fun onResponse(call: Call<WeatherModel.Weather>, response: Response<WeatherModel.Weather>) {
                    Timber.v("API Url call ${call.request().url()}")
                    if (!response.isSuccessful || response.body() == null) {
                        return
                    }
                    // Pass information to the view
                    weatherApiResponseLiveData.value = response.body()
                }

                override fun onFailure(call: Call<WeatherModel.Weather>, throwable: Throwable) {
                    Timber.e(throwable, "Error trying to fetch the user's location forecast.")
                }
            })
    }

    fun getUsersCurrentLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
        if (checkUserLocationPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener(this).addOnFailureListener(this)
        }
    }

    override fun onSuccess(location: Location?) {
        if (location == null) {
            Timber.v("User location's null, falling back to default location.")
            locationNameLiveData.value = DEFAULT_LOCATION_NAME
        } else {
            Timber.v("User location: $location")
            userLocation = location
            getLocationName()
        }
        fetchForecastAtLocation(userLocation.latitude, userLocation.longitude)
    }

    override fun onFailure(exception: Exception) {
        Timber.e(exception, "Error trying to get last user location")
    }

    private fun checkUserLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(appContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            requestLocationPermissionLiveData.value = true
            false
        }
    }

    private fun getLocationName() {
        MapboxGeocoding.builder()
            .accessToken(MAPBOX_ACCESS_TOKEN)
            .query(Point.fromLngLat(userLocation.longitude, userLocation.latitude))
            .geocodingTypes(GeocodingCriteria.TYPE_PLACE)
            .build().enqueueCall(this)
    }

    override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
        if (response.isSuccessful && response.body()!!.features().isNotEmpty()) {
            Timber.v("Successfully reverse geocoded location")
            Timber.v("Location name: ${response.body()?.features()!![0].text()}")
            locationNameLiveData.value = response.body()?.features()!![0].text()
        }
    }

    override fun onFailure(call: Call<GeocodingResponse>, throwable: Throwable) {
        Timber.e(throwable, "Error trying to reverse geocode location")
    }

    override fun onPlaceSelected(carmenFeature: CarmenFeature?) {
        if (carmenFeature == null) {
            Timber.e("Place selected returned a null carmen feature.")
            return
        }

        if (carmenFeature.center() != null) {
            locationNameLiveData.value = carmenFeature.text()
            fetchForecastAtLocation(carmenFeature.center()?.latitude()!!, carmenFeature.center()?.longitude()!!)
        } else {
            Timber.e("carmenFeature center's null.")
            // TODO display error to user
        }

        userFinishedSearchLiveData.value = true
    }

    override fun onCancel() {
        userFinishedSearchLiveData.value = true
    }
}