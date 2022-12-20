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
    private val repository: RWBRepository

    init {
        val userDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = RWBRepository(userDao)
        readAllData = repository.readAllData
        readLevel = repository.readLevel
    }

    fun levelUp(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.levelUp()
        }
    }

    fun changeExperience(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeExperience()
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPet(pet)
        }
    }



    /*fun readLevel(): Int {
        viewModelScope.launch(Dispatchers.IO) {
            repository.readLevel()
        }*/

}