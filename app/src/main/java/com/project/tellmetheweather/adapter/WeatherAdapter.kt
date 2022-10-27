package com.project.tellmetheweather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.tellmetheweather.R
import com.project.tellmetheweather.api.ForcecastMain

class WeatherAdapter(private val context: Context, private val datas: ArrayList<ForcecastMain>): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    //WeatherAdapter와 item_list를 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return WeatherAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_forecast_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //데이터를 순서대로 모든 값 바인딩
        holder.bind(datas[position], context)
    }

    override fun getItemCount() = datas.size //어탭터로 바인딩된 아이템 개수 반환

    class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        private val iconImage = view?.findViewById<ImageView>(R.id.iconImageView)
        private val realtime = view?.findViewById<TextView>(R.id.timeTextView)
        private val temp = view?.findViewById<TextView>(R.id.tempTextView)

        @SuppressLint("SetTextI18n")
        fun bind(data: ForcecastMain, context: Context){
            data.weather.forEach {
                val icon = it.icon
                val iconUrl = "http://openweathermap.org/img/w/" + icon + ".png"
                Glide.with(context).load(iconUrl).into(iconImage!!)
            }
            temp?.text = (String.format("%.1f", data.main.temp - 273)) + "°C"

            val str = data.time
            val subString = str.substring(5 until 10)
            val subString2 = str.substring(11 until 16)
            realtime?.text = subString + "\n" + subString2

//            data.time.split()
//            val x = data.time
//            val y = data.time
//            val result = "${x} ${y}".split("-").toTypedArray()
//            val a = result[1]
//            val b = result[2]
//            realtime?.text = a + "." + b
        }
    }


}