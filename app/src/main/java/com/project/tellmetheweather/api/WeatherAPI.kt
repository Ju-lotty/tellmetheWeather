package com.project.tellmetheweather.api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    fun getRealtime(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): Call<RealtimeDTO>

    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("lang") lang: String
    ) : Call<ForcecastDTO>
}

