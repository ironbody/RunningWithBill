package com.example.runningwithbill

// this code is taken from a tutorial by Philipp Lackner,
// "How to Track Your Users Location in the Background in Android - Android Studio Tutorial" on youtube
// 2022

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