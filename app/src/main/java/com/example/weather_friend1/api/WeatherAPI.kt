package com.example.weather_friend1.api
// Retrofit interface

import com.example.weather_friend1.WeatherModel
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


    @GET("data/2.5/weather?&units=metric&APPID=4581e0cb455b9666721f4e5f7b85175e")
    fun getData(
        @Query("q") cityName: String,
    ): Single<WeatherModel>




}

