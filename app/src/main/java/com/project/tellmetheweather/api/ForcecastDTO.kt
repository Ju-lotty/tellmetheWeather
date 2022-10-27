package com.project.tellmetheweather.api

import com.google.gson.annotations.SerializedName

data class ForcecastDTO(
    @SerializedName("list") val list: List<ForcecastMain>
)

data class ForcecastMain(
    @SerializedName("main") val main: ForcecasTemp,
    @SerializedName("weather") val weather: List<ForcecasWeather>,
    @SerializedName("dt_txt")val time: String
)

data class ForcecasTemp(
    val temp: Double
)

data class ForcecasWeather(
    val description: String,
    val icon: String
)


