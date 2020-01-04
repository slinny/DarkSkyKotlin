package com.example.android.darkskykotlin.models


import com.google.gson.annotations.SerializedName

data class Daily(
    val summary: String,
    val icon: String,
    val `data`: List<Data>
)