package com.example.weather_friend1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDatabaseBuilder {


    companion object {
        const private val BASE_URL_MockAPI="https://61c896cbadee460017260d70.mockapi.io/"

        fun getRetroBuilder():Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL_MockAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}