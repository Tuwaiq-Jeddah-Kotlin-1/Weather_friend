package com.example.weather_friend1

import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.weather_friend1.ui.CityWeather

class PageAdapter(fm: FragmentManager, val list: List<WeatherModel>,val city:List<String>) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return list.size;
        }

        override fun getItem(position: Int): Fragment {

            return CityWeather(list.filter {it.name== city[position]}.get(0))

        }

    }

