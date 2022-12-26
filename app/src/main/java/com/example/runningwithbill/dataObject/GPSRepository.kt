package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData

class GPSRepository(private val RWBDao: RWBDao) {
    suspend fun addGpsCords(gps: Gps){
        RWBDao.addGpsCords(gps)
    }
}