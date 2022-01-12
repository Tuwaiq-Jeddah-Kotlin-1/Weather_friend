package com.example.weather_friend1

import android.content.Context
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.example.weather_friend1.ui.CityWeather


class PageAdapter(fm: FragmentManager, val list: List<WeatherModel>,val context:Context) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return list.size;
        }

        override fun getItem(position: Int): Fragment {
            val weatherState =list[position].weather[0].icon
            val description = list[position].weather[0].description
            val nameCity = list[position].name




            return CityWeather(list[position],weatherState,nameCity,description)
//            list.filter {it.name== city[position]}.get(0)
        }
}

