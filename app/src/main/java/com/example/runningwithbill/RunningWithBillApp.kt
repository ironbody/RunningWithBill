package com.example.runningwithbill

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.runningwithbill.location.LocationService

class RunningWithBillApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            LocationService.channelId,
            "Location",
            NotificationManager.IMPORTANCE_LOW
        )

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}