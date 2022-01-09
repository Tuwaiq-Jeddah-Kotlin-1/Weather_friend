package com.example.weather_friend1.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weather_friend1.*
import com.example.weather_friend1.viewmodel.MainViewModel


private const val TAG = "CityWeather"
private lateinit var DataWeather: LinearLayout
private lateinit var TvCityCode: TextView
private lateinit var TvCityName: TextView
private lateinit var Tvhumidity: TextView
private lateinit var TvDegree: TextView
private lateinit var TvWindSpeed: TextView


//private lateinit var ImageWeather:ImageView
private lateinit var iconDay: LottieAnimationView

class CityWeather(var cityWeather: WeatherModel) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_city_weather,
            container, false
        )
    }

    private lateinit var viewmodel: MainViewModel


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view, savedInstanceState
        )

        DataWeather = view.findViewById(R.id.ll_data)
        TvCityCode = view.findViewById(R.id.tv_city_code)
        TvCityName = view.findViewById(R.id.tv_city_name)
        Tvhumidity = view.findViewById(R.id.tv_humidity)
        TvDegree = view.findViewById(R.id.tv_degree)
        TvWindSpeed = view.findViewById(R.id.tv_wind_speed)
        iconDay = view.findViewById(R.id.img_weather_pictures)





        TvCityCode.text = cityWeather.sys.country
        TvCityName.text = cityWeather.name


//        Glide.with(this)
//            .load("https://openweathermap.org/img/wn/" + cityWeather.weather.get(0).icon + "@2x.png")
//            .into(ImageWeather)

        val weatherState = cityWeather.weather.get(0).icon.trim()
        when (weatherState) {
            "01d" -> iconDay.setAnimation(R.raw.weather_sunny)
            "02d" -> iconDay.setAnimation(R.raw.weather_partly_cloudy)
            "03d" -> iconDay.setAnimation(R.raw.weather_windy)
            "04d" -> iconDay.setAnimation(R.raw.weather_windy)
            "09d" -> iconDay.setAnimation(R.raw.weather_storm)
            "10d" -> iconDay.setAnimation(R.raw.weather_partly_shower)
            "11d" -> iconDay.setAnimation(R.raw.weather_thunder)
            "13d" -> iconDay.setAnimation(R.raw.weather_snow_sunny)
            "50d" -> iconDay.setAnimation(R.raw.weather_mist)
            "01n" -> iconDay.setAnimation(R.raw.weather_night)
            "02n" -> iconDay.setAnimation(R.raw.weather_cloudynight)
            "03n" -> iconDay.setAnimation(R.raw.weather_windy)
            "04n" -> iconDay.setAnimation(R.raw.weather_windy)
            "09n" -> iconDay.setAnimation(R.raw.weather_rainynight)
            "10n" -> iconDay.setAnimation(R.raw.weather_rainynight)
            "11n" -> iconDay.setAnimation(R.raw.weather_thunder)
            "13n" -> iconDay.setAnimation(R.raw.weather_snownight)
            "50n" -> iconDay.setAnimation(R.raw.weather_mist)
            else -> iconDay.setAnimation(R.raw.weather_mist)

        }
        Log.e("weather", "${weatherState}")



        TvDegree.text = cityWeather.main.temp.toString() + "°C"
        Tvhumidity.text = cityWeather.main.humidity.toString() + "%"
        TvWindSpeed.text = cityWeather.wind.speed.toString()



    }


}
