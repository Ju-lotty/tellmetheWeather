package com.project.tellmetheweather.api


data class RealtimeDTO (
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind
)

data class Weather(
    val main: String?,
    val description: String?,
    val icon: String?
)

data class Main(
    val temp: Double?,
    val humidity: Int?,
    val temp_min: Double?,
    val temp_max: Double?
)

data class Wind(
    val speed: Double?,
)
