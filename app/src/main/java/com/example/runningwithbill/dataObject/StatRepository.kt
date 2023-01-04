package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData

class StatRepository(private val RWBDao: RWBDao) {

    val readDay: LiveData<Int> = RWBDao.readDay()
    val readWeek: LiveData<Int> = RWBDao.readWeek()
    val readMonth: LiveData<Int> = RWBDao.readMonth()
    val readTotal: LiveData<Int> = RWBDao.readTotal()


    suspend fun addStats(stats: Stats){
        RWBDao.addStats(stats)
    }

    suspend fun setMetersDay(meters: Int){
        RWBDao.setMetersDay(meters)
    }

    suspend fun setMetersWeek(meters: Int){
        RWBDao.setMetersWeek(meters)
    }
    suspend fun setMetersMonth(meters: Int){
        RWBDao.setMetersMonth(meters)
    }

    suspend fun setMetersTotal(meters: Int){
        RWBDao.setMetersTotal(meters)
    }


}