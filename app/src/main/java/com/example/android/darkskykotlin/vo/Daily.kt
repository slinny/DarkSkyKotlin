package com.example.android.darkskykotlin.vo


data class Daily(
    val summary: String,
    val icon: String,
    val `data`: List<DailyData>
)