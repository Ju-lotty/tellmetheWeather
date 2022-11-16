package com.project.tellmetheweather

//noinspection SuspiciousImport

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.tellmetheweather.adapter.WeatherAdapter
import com.project.tellmetheweather.api.ForcecastDTO
import com.project.tellmetheweather.api.ForcecastMain
import com.project.tellmetheweather.api.RealtimeDTO
import com.project.tellmetheweather.api.WeatherAPI
import com.project.tellmetheweather.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    private val HOST_URL = "https://api.openweathermap.org/"
    private var cityName = "Seoul"
    private val API_KEY = "fd9d7c1642ccbdc418961d9dd15c971d"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)



        locationinit()
        getRealtime()
        getForecast()
        timeinit()
        refreshButtoninit()

    }
    private fun setAdapter(dataList : ArrayList<ForcecastMain>) {
        val myAdapter = WeatherAdapter(this, dataList)
        binding.forecastRecyclerView.adapter = myAdapter
        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(this@WeatherActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.forecastRecyclerView.setHasFixedSize(false)
    }



    private fun locationinit() = with(binding) {
        val cityList = listOf("서울", "인천", "춘천", "강릉", "대전", "충청북도", "충청남도", "대구", "전주", "목포", "광주", "부산", "제주")
        val adapter = ArrayAdapter(this@WeatherActivity, R.layout.style_spinner, cityList)
        adapter.setDropDownViewResource(R.layout.style_spinner2)
        spinnerLocation.adapter = adapter

        spinnerLocation.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        cityName = "Seoul"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    1 -> {
                        cityName = "Incheon"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    2 -> {
                        cityName = "Chuncheon-si"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    3 -> {
                        cityName = "Gangneung-si"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    4 -> {
                        cityName = "Daejeon"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    5 -> {
                        cityName = "Chungcheongbuk-do"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    6 -> {
                        cityName = "Chungcheongnam-do"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    7 -> {
                        cityName = "Daegu"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    8 -> {
                        cityName = "Jeonju-si"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    9 -> {
                        cityName = "Mokpo-si"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    10 -> {
                        cityName = "Gwangju"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    11 -> {
                        cityName = "Busan"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                    12 -> {
                        cityName = "Jeju-do"
                        getRealtime()
                        timeinit()
                        getForecast()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    private fun getRealtime() = with(binding) {
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI = retrofit.create(WeatherAPI::class.java)
        val call = weatherAPI.getRealtime(cityName, API_KEY)
        call.enqueue(object : Callback<RealtimeDTO> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<RealtimeDTO>, response: Response<RealtimeDTO>) {
                if(response.isSuccessful) {

                    val now = (String.format("%.1f", response.body()!!.main.temp!! - 273))
                    val max = (String.format("%.1f",response.body()!!.main.temp_max!! - 273)) + "°C"
                    val min = (String.format("%.1f",response.body()!!.main.temp_min!! - 273)) + "°C"

                    temperatureTextView.text = now
                    maxvalueTextView.text = max
                    minvalueTextView.text = min
                    humidityTextView.text = response.body()!!.main.humidity.toString() + "%"
                    windspeedTextView.text = response.body()!!.wind.speed.toString() + "km/h"

                }
            }

            override fun onFailure(call: Call<RealtimeDTO>, t: Throwable) {
                Log.d("결과3", "실패! $t")
            }
        })
    }

    private fun getForecast() {

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI = retrofit.create(WeatherAPI::class.java)
        val call = weatherAPI.getForecast(cityName, API_KEY, "kr")
        call.enqueue(object: Callback<ForcecastDTO> {
            override fun onResponse(call: Call<ForcecastDTO>, response: Response<ForcecastDTO>) {
                if(response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        setAdapter(it.list as ArrayList<ForcecastMain>)
                    }
                }
            }

            override fun onFailure(call: Call<ForcecastDTO>, t: Throwable) {
                Log.d("포케스트 결과", "실패")
            }
        })
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeinit() = with(binding) {
        val today = LocalDate.now().toString()
        todayTextView.text = today

        val hour = LocalDateTime.now().hour.toString()
        val minute = LocalDateTime.now().minute.toString()

        nowTimeTextView.text = hour + ":" + minute

        if(hour.toInt() >= 12) {
            nowTimeTextView.text = "오후 " + hour + ":" + minute
        } else {
            nowTimeTextView.text = "오전 " + hour + ":" + minute
        }

        if(18 <= hour.toInt() || 0 == hour.toInt() || hour.toInt() <= 6) {
            backGround.setBackgroundResource(R.drawable.nightbackground)
            view1.setBackgroundResource(R.drawable.shape2)
            view2.setBackgroundResource(R.drawable.shape2)
            weatherImageView.setBackgroundResource(R.drawable.ic_night)
        } else {
            backGround.setBackgroundResource(R.color.moring)
            view1.setBackgroundResource(R.drawable.shape1)
            view2.setBackgroundResource(R.drawable.shape1)
            weatherImageView.setBackgroundResource(R.drawable.ic_afternoon)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun refreshButtoninit() = with(binding) {
        refreshButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            this@WeatherActivity.timeinit()
            getRealtime()
            progressBar.visibility = View.GONE
            Toast.makeText(this@WeatherActivity, "날씨가 갱신되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
