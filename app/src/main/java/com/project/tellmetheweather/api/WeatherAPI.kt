package com.project.tellmetheweather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather?")
    fun getReuslt(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): Call<ResultDTO>
}
