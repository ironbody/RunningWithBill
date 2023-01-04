package com.example.runningwithbill.location

// this code is taken from a tutorial by Philipp Lackner,
// "How to Track Your Users Location in the Background in Android - Android Studio Tutorial" on youtube
// 2022

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.runningwithbill.R
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

const val FIVE_SEC: Long = 5 * 1000
const val MIN_DIST: Float = 0f

class LocationService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient
    private val notificationId = 1

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = LocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val stopTrackingIntent = Intent(applicationContext, this::class.java)
        stopTrackingIntent.action = ACTION_STOP
        val intent =
            PendingIntent.getService(this, 0, stopTrackingIntent, PendingIntent.FLAG_IMMUTABLE)
        val stopAction = NotificationCompat.Action(R.drawable.ic_baseline_circle_24, "Stop", intent)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Tracking location")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_baseline_pets_24)
            .setOngoing(true)
            .addAction(stopAction)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(FIVE_SEC, MIN_DIST)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude
                val long = location.longitude
                val updatedNotification = notification.setContentText(
                    "Location: ($lat, $long)"
                )
                notificationManager.notify(notificationId, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(notificationId, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val channelId = "location"
    }
}