package com.example.weather_friend1.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.weather_friend1.R
import com.example.weather_friend1.WeatherModel





private const val TAG ="CityWeather"
private lateinit var Error:TextView
private lateinit var DataWeather:LinearLayout
private lateinit var TvCityCode:TextView
private lateinit var TvCityName:TextView
private lateinit var Tvhumidity:TextView
private lateinit var TvDegree:TextView
private lateinit var TvWindSpeed:TextView
private lateinit var ImageWeather:ImageView
class CityWeather(var cityWeather: WeatherModel) : Fragment() {



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            return inflater.inflate(
                R.layout.fragment_scrolling_city_weather,
                container, false)
        }private lateinit var viewmodel: MainViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState
        )

        DataWeather=view.findViewById(R.id.ll_data)
        TvCityCode=view.findViewById(R.id.tv_city_code)
        TvCityName=view.findViewById(R.id.tv_city_name)
        Tvhumidity=view.findViewById(R.id.tv_humidity)
        TvDegree=view.findViewById(R.id.tv_degree)
        TvWindSpeed=view.findViewById(R.id.tv_wind_speed)
        ImageWeather=view.findViewById(R.id.img_weather_pictures)



        TvCityCode.text = cityWeather.sys.country
        TvCityName.text = cityWeather.name


        Glide.with(this)
            .load("https://openweathermap.org/img/wn/" + cityWeather.weather.get(0).icon + "@2x.png")
            .into(ImageWeather)


        TvDegree.text = cityWeather.main.temp.toString() + "°C"
        Tvhumidity.text = cityWeather.main.humidity.toString() + "%"
        TvWindSpeed.text = cityWeather.wind.speed.toString()




    }


}
