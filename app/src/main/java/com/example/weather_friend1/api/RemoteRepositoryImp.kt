package com.example.weather_friend1.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(private val api:UserDatabaseAPI) : RemoteRepository {
    override suspend fun getAPIUser() =
        withContext(Dispatchers.IO) {
api.getAPIUser()

        }


}


