package com.example.weather_friend1.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.airbnb.lottie.LottieAnimationView
import com.example.weather_friend1.*
import com.example.weather_friend1.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import java.util.concurrent.TimeUnit


private lateinit var DataWeather: LinearLayout
private lateinit var TvCityCode: TextView
private lateinit var TvCityName: TextView
private lateinit var Tvhumidity: TextView
private lateinit var TvDegree: TextView
private lateinit var TvWindSpeed: TextView
private lateinit var iconDay: LottieAnimationView
lateinit var webView: WebView

class CityWeather(
    var cityWeather: WeatherModel,
    val weatherState1: String,
    val nameCity: String,
    val description: String,
) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(
            R.layout.fragment_city_weather,
            container, false
        )
    }



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

        IconLottie(weatherState1)
        val strWeather = Recommendation(weatherState1, nameCity, description)


        TvCityCode.text = cityWeather.sys.country
        TvCityName.text = cityWeather.name
        TvDegree.text = cityWeather.main.temp.toString() + "°C"
        Tvhumidity.text = cityWeather.main.humidity.toString() + "%"
        TvWindSpeed.text = cityWeather.wind.speed.toString()



        val data = Data.Builder().putInt(NotificationWorker.NOTIFICATION_ID, 0).build()
        val quoteNotification =
            Data.Builder().putString(NotificationWorker.NOTIFICATION_CONTENT_ID, strWeather)
                .build()

        val notificationWork =
            OneTimeWorkRequest.Builder(NotificationWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES).setInputData(data)
                .setInputData(quoteNotification)
                .build()

        val instanceWorkManager = WorkManager.getInstance(view.context)

        instanceWorkManager.beginUniqueWork(
            NotificationWorker.NOTIFICATION_WORK,
            ExistingWorkPolicy.REPLACE,
            notificationWork
        ).enqueue()

        webView = view.findViewById(R.id.webView1)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://openweathermap.org/weathermap?basemap=map&cities=true&layer=clouds&lat=${cityWeather.coord.lat}&lon=${cityWeather.coord.lon}&zoom=3")
        webView.webChromeClient = object : WebChromeClient() {



        }
    }

    private fun IconLottie(weatherState: String) {
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
    }

    private fun Recommendation(
        weatherState: String,
        nameCity: String,
        description: String,
    ): String {
        val strWeather = when (weatherState) {
            "01d" -> "in ${nameCity} \n Enjoy a time \uD83E\uDD29 that looks like weather: ${description}"
            "02d" -> " in ${nameCity} \uD83C\uDF28 weather: ${description}"
            "03d" -> " in ${nameCity} \nwhether seems ${description} \uD83C\uDF28 "
            "04d" -> " in ${nameCity} \nwhether seems ${description} \uD83C\uDF28 "
            "09d" -> " in ${nameCity} \nwhether seems ${description} , Be careful \uD83C\uDF2A "
            "10d" -> " in ${nameCity} \nDon't forget to take the umbrella ☔ whether seems , ${description}"
            "11d" -> " in ${nameCity} \nwhether seems ${description} \uD83C\uDF29"
            "13d" -> " in ${nameCity} \nDon't forget to take your coat \uD83E\uDDE3 , whether seems , ${description}"
            "50d" -> " in ${nameCity} \n whether seems , ${description} \uD83C\uDF00"
            "01n" -> " in ${nameCity} \n Good night \uD83E\uDD29 ,whether seems , ${description}"
            "02n" -> " in ${nameCity} \n Cloudy weather \uD83C\uDF28, ${description}"
            "03n" -> " in ${nameCity} \n whether seems ${description}  \uD83C\uDF28 "
            "04n" -> " in ${nameCity} \n whether seems ${description}  \uD83C\uDF28 "
            "09n" -> " in ${nameCity} \n Don't forget to take the umbrella \u2614 \"whether seems , ${description}"
            "10n" -> "weather in ${nameCity} \nDon't forget to take the umbrella ☔ whether seems , ${description}"
            "11n" -> " in ${nameCity} \n whether seems ${description} \uD83C\uDF29"
            "13n" -> "in ${nameCity} \nDon't forget to take your coat \uD83E\uDDE3 , whether seems , ${description}"
            "50n" -> " in ${nameCity} \n whether seems , ${description} \uD83C\uDF00"
            else -> description

        }
        return strWeather
    }

}



























//Log.e("ListWeather ","${cityWeather}")

//        Glide.with(this)
//            .load("https://openweathermap.org/img/wn/" + cityWeather.weather.get(0).icon + "@2x.png")
//            .into(ImageWeather)
