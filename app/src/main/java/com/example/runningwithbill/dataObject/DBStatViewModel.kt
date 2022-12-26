package com.example.runningwithbill.dataObject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBStatViewModel(application: Application): AndroidViewModel(application){

    private val repository: StatRepository

    init {
        val rwbDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = StatRepository(rwbDao)
    }

}