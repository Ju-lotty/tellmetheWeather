package com.project.tellmetheweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.tellmetheweather.databinding.ActivityFineDustBinding

class FineDustActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFineDustBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFineDustBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gobackintit()
    }
    private fun gobackintit() = with(binding) {
        backButton.setOnClickListener {
            val intent = Intent(this@FineDustActivity, WeatherActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}