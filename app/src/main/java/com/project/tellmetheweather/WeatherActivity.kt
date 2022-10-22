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
import com.project.tellmetheweather.databinding.ActivityWeatherBinding
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
