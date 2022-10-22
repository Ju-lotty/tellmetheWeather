package com.project.tellmetheweather.api


import com.google.gson.annotations.SerializedName

data class ResultDTO (
    @SerializedName("weather") val weather: Array<Weather>,
    @SerializedName("main") val main: Array<Main>,
    @SerializedName("wind") val wind: Array<Wind>
)
data class Weather (
    var main: String,
    var icon: String,
    var description: String
)

data class Main (
    var temp: Double,
    var humidity: Double,
    var feels_like: Double,

    var temp_min: Double,
    var temp_max: Double,
)

data class Wind (
    var speed: Double
)



