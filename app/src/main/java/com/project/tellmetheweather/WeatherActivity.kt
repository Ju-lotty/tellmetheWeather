package com.project.tellmetheweather

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.project.tellmetheweather.api.ResultDTO
import com.project.tellmetheweather.api.WeatherAPI
import com.project.tellmetheweather.databinding.ActivityWeatherBinding
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intentinit()
        timeinit()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI = retrofit.create(WeatherAPI::class.java)
        val call = weatherAPI.getReuslt("Seoul", "fd9d7c1642ccbdc418961d9dd15c971d")
        call.enqueue(object: Callback<ResultDTO> {
            override fun onResponse(call: Call<ResultDTO>, response: Response<ResultDTO>) {
                val a = response.raw().toString()
                val b = response.body().toString()
                Log.d("결과1", "$a")
                Log.d("결과2", "$b")
            }

            override fun onFailure(call: Call<ResultDTO>, t: Throwable) {
                Log.d("결과", "Fail!!!!!")
            }

        })

    }


    private fun intentinit() = with(binding) {
        gotoActivityButton.setOnClickListener {
            Log.d("gotoActivityButton", "성공")
            val intent = Intent(this@WeatherActivity, FineDustActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
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
    }

}
