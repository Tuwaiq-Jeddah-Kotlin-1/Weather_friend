package com.example.weather_friend1

import androidx.work.*
import com.example.weather_friend1.ui.MainActivity
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class StockRepo {
    fun returnStockChange(mainActivity: MainActivity): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val oneTimeWorker = OneTimeWorkRequest
            .Builder(StockUpdateWorker::class.java)
            .setConstraints(constraints)
            .build()
//        val periodicWorker = PeriodicWorkRequest
//            .Builder(StockUpdateWorker::class.java, 15, TimeUnit.SECONDS)
//            .build()
        WorkManager.getInstance(mainActivity).enqueue(oneTimeWorker)
        //WorkManager.getInstance().cancelAllWork()
//        WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
//            "periodicStockWorker",
//            ExistingPeriodicWorkPolicy.KEEP,
//           // periodicWorker
//            )
        //WorkManager.getInstance().cancelUniqueWork("periodicStockWorker")

        return oneTimeWorker
    }
}