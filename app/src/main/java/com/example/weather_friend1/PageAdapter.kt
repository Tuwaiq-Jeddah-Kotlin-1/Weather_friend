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

//        override fun getPageTitle(position: Int): CharSequence? {
//            when (position) {
//                0 -> {
//                    return "Tab 1"
//                }
//                1 -> {
//                    return "Tab 2"
//                }
//                2 -> {
//                    return "Tab 3"
//                }
//            }
//            return super.getPageTitle(position)
//        }
    }

