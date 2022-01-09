package com.example.weather_friend1.viewmodel

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*

import com.example.weather_friend1.R
import com.example.weather_friend1.WeatherModel
import com.example.weather_friend1.api.WeatherAPIService
import com.example.weather_friend1.ui.AppRepo
import com.example.weather_friend1.ui.CitesUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

private const val TAG = "MainViewModel"

class MainViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = AppRepo(context)
    private val weatherApiService = WeatherAPIService()
    private val disposable = CompositeDisposable()



    fun getDataFromAPI(cityName: List<String>): MutableLiveData<List<WeatherModel>> {
        val weather_data = MutableLiveData<List<WeatherModel>>()



        cityName.forEach {
            disposable.add(
                weatherApiService.getDataService(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {

                        override fun onSuccess(t: WeatherModel) {
                            var tempList: MutableList<WeatherModel> = arrayListOf()
                            if (weather_data.value != null) tempList =
                                weather_data.value as MutableList<WeatherModel>
                            tempList.add(t)
                            weather_data.value = tempList
                            Log.d(TAG, "onSuccess: Success")
                        }

                        override fun onError(e: Throwable) {

                            Log.e(TAG, "onError: " + e)

                        }

                    })
            )
        }

        Log.e(TAG, "weather_data Final: ${weather_data.value}")
        return weather_data
    }




    fun newList(resources: Resources): LiveData<List<String>> {
        val list3 = resources.openRawResource(R.raw.city_list_line)
        val data: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
        viewModelScope.launch {

            withContext(Dispatchers.Unconfined) {
                val bufferedReader = BufferedReader(InputStreamReader(list3))
                data.value = bufferedReader.readLine().split(",").map {
                    it.removeSurrounding("\"", "\"").trim()// .substring(0, it.lastIndex)

                }


            }
        }
        return data
    }
   fun getAllCites(): MutableLiveData<MutableList<CitesUser>> {
        val cites = MutableLiveData<MutableList<CitesUser>>()
        viewModelScope.launch {
            cites.postValue(repo.getAllCites() as MutableList<CitesUser>?)
        }
        return cites
    }


    fun insertDB(citesUser: CitesUser) = viewModelScope.launch {
        repo.InsertDB(citesUser)

    }
    fun updateData(citesUser: CitesUser) = viewModelScope.launch {
        repo.UpdateCites(citesUser)

    }
    fun deletData(citesUser: CitesUser) = viewModelScope.launch {
        repo.DeleteCites(citesUser)

    }

    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }



}

