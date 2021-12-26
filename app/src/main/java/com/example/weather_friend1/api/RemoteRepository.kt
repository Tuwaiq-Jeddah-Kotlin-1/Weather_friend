package com.example.weather_friend1.api

import com.example.weather_friend1.model_mock_api.MockAPIItem
import retrofit2.Response

interface RemoteRepository {
    suspend fun getAPIUser(): Response<List<MockAPIItem>>

}