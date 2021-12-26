package com.example.weather_friend1.ui


import android.app.Application
import com.example.weather_friend1.ui.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepo(context:Application) {
  private val appDB = AppDataBase.getDatabase(context)



  suspend fun getAllCites(): List<CitesUser> = withContext(Dispatchers.IO) {
    appDB.citesDao().getAllCites()
  }

  suspend fun InsertDB(citesUser: CitesUser) = withContext(Dispatchers.IO) {


      appDB.citesDao().insert(citesUser)

    }

  suspend fun UpdateCites(citesUser: CitesUser) = withContext(Dispatchers.IO) {

      appDB.citesDao().update(citesUser)

    }
    suspend fun DeleteCites(citesUser: CitesUser) = withContext(Dispatchers.IO) {

        appDB.citesDao().delete(citesUser)

      }



  }
