package com.example.runningwithbill.location

// this code is taken from a tutorial by Philipp Lackner,
// "How to Track Your Users Location in the Background in Android - Android Studio Tutorial" on youtube
// 2022

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.example.runningwithbill.R
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

const val UPDATE_INTERVAL: Long = 5 * 1000
const val MIN_DIST: Float = 3f

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

        val distanceTravelled = getDistanceTravelledSinceLastFunction()
        locationClient
            .getLocationUpdates(UPDATE_INTERVAL, MIN_DIST)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val newLocations = locations.value?.toMutableList()?.apply {
                    add(location)
                }
                newLocations?.let{
                    locations.postValue(it)
                }
                val distance = distanceTravelled(location)
                // Log.d("Data", "start")
                totalDistanceWalked.postValue(totalDistanceWalked.value?.plus(distance))
                // totalDistanceWalked.value?.toString()?.let { Log.d("Data", it) }

                val updatedNotification = notification.setContentText(
                    "Location: (${totalDistanceWalked.value?.roundToInt()} m)"
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

    private fun getDistanceTravelledSinceLastFunction(): (Location) -> (Float) {
        var previous: Location? = null
        return { location ->
            previous?.let {
                val results: FloatArray = FloatArray(1)
                Location.distanceBetween(
                    it.latitude,
                    it.longitude,
                    location.latitude,
                    location.longitude,
                    results
                )
                results[0]
            } ?: run {
                previous = location
                0f
            }
        }
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val channelId = "location"

        val locations =  MutableLiveData<List<Location>>(mutableListOf())
        val totalDistanceWalked = MutableLiveData(0f)
    }
}