package com.example.runningwithbill.dataObject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBRWBViewModel(application: Application): AndroidViewModel(application){

    @kotlin.jvm.JvmField
    var readLevel: LiveData<Int>

    @kotlin.jvm.JvmField
    var readAllData: LiveData<List<Pet>>
    private val repository: PetRepository

    init {
        val rwbDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = PetRepository(rwbDao)
        readAllData = repository.readAllData
        readLevel = repository.readLevel
    }

    fun levelUp(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.levelUp()
        }
    }

    fun addExperience(xp: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExperience(xp)
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPet(pet)
        }
    }

    fun addGpsCords(gps: Gps) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGpsCords(gps)
        }
    }


}