package com.example.runningwithbill.dataObject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBStatViewModel(application: Application): AndroidViewModel(application){

    @kotlin.jvm.JvmField
    var readDay: LiveData<Int>
    @kotlin.jvm.JvmField
    var readWeek: LiveData<Int>
    @kotlin.jvm.JvmField
    var readMonth: LiveData<Int>
    @kotlin.jvm.JvmField
    var readTotal: LiveData<Int>

    private val repository: StatRepository


    init {
        val rwbDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = StatRepository(rwbDao)
        readDay = repository.readDay
        readWeek = repository.readWeek
        readMonth = repository.readMonth
        readTotal = repository.readTotal

    }

    fun addStats(stats: Stats){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStats(stats)
        }
    }

    fun setMetersDay(meters: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setMetersDay(meters)
        }
    }

    fun setMetersWeek(meters: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setMetersWeek(meters)
        }
    }

    fun setMetersMonth(meters: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setMetersMonth(meters)
        }
    }

    fun setMetersTotal(meters: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setMetersTotal(meters)
        }
    }
}