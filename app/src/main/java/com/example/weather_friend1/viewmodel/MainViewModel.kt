package com.mrcaracal.havadurumumrc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_friend1.WeatherModel
import com.example.weather_friend1.api.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val weatherApiService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    //val weather_error = MutableLiveData<Boolean>()
  //  val weather_loading = MutableLiveData<Boolean>()

    fun getDataFromAPI(cityName: List<String>):MutableLiveData<List<WeatherModel>>{
        val weather_data = MutableLiveData<List<WeatherModel>>()

       // weather_loading.value = true

            cityName.forEach{
        disposable.add(
                weatherApiService.getDataService(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {

                        override fun onSuccess(t: WeatherModel) {
                            var tempList: MutableList<WeatherModel> = arrayListOf()
                              if (weather_data.value!=null) tempList = weather_data.value as MutableList<WeatherModel>
                            tempList.add(t)
                            weather_data.value=tempList
//                        weather_error.value = false
//                        weather_loading.value = false
                            Log.d(TAG, "onSuccess: Success")
                        }

                        override fun onError(e: Throwable) {
//                        weather_error.value = true
//                        weather_loading.value = false
                            Log.e(TAG, "onError: " + e)
                        }

                    }))
            }


return weather_data
    }

}