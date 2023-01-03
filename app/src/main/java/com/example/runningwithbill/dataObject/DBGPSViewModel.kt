package com.example.runningwithbill.dataObject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBGPSViewModel(application: Application): AndroidViewModel(application){

    private val repository: GPSRepository

    init {
        val rwbDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = GPSRepository(rwbDao)
    }

    fun addGpsCords(gps: Gps) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGpsCords(gps)
        }
    }


}