package com.example.weather_friend1

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.weather_friend1.ui.MainActivity
import kotlin.random.Random

class StockUpdateWorker(val context: Context, workparams: WorkerParameters): Worker(context, workparams) {
    override fun doWork(): Result {
//        Log.d("worker", "Work Requested!! $randomValue")
        // Notification
        val name= "Weather Today"
        var intent = Intent(context, MainActivity::class.java)
        val pendingActivity = PendingIntent.getActivity(context,0,intent, 0)
        val notification = NotificationCompat
            .Builder(context, "NOTIFICATION_CHANNEL_ID")
            .setTicker(name)
            .setSmallIcon(android.R.drawable.btn_star_big_on)
            .setContentTitle(name)
            .setContentIntent(pendingActivity)
            .setAutoCancel(true)
            .setContentText(" /////// ")
            .build()
        val notificationManager = NotificationManagerCompat
            .from(context)
        notificationManager.notify(0, notification)


        return Result.success()
    }
}