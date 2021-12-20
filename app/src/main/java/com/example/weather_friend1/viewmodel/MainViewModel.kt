package com.mrcaracal.havadurumumrc.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_friend1.R
import com.example.weather_friend1.WeatherModel
import com.example.weather_friend1.api.WeatherAPIService
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

class MainViewModel : ViewModel() {

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
}

