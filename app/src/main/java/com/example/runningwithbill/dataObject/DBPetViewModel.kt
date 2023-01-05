package com.example.runningwithbill.dataObject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBPetViewModel(application: Application): AndroidViewModel(application){

    @kotlin.jvm.JvmField
    var readLevel: LiveData<Int>
    @kotlin.jvm.JvmField
    var readExperience: LiveData<Int>
    @kotlin.jvm.JvmField
    var readHealth: LiveData<Int>
    @kotlin.jvm.JvmField
    var readFood: LiveData<Int>
    @kotlin.jvm.JvmField
    var readBeakLenght: LiveData<Int>
    @kotlin.jvm.JvmField
    var readHatHeight: LiveData<Int>
    @kotlin.jvm.JvmField
    var readBodySize: LiveData<Int>
    @kotlin.jvm.JvmField
    var readStatPoints: LiveData<Int>

    @kotlin.jvm.JvmField
    var readAllData: LiveData<List<Pet>>
    private val repository: PetRepository

    init {
        val rwbDao = RWBDatabase.getDatabase(application).RWBDao()
        repository = PetRepository(rwbDao)
        readAllData = repository.readAllData
        readLevel = repository.readLevel
        readExperience = repository.readExperience
        readHealth = repository.readHealth
        readFood = repository.readFood
        readBeakLenght = repository.readBeakLength
        readHatHeight = repository.readHatHeight
        readBodySize = repository.readBodySize
        readStatPoints = repository.readStatPoints
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

    fun addHealth(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHealth(value)
        }
    }

    fun addFood(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(value)
        }
    }

    fun addBeakLength(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBeakLength(value)
        }
    }

    fun addHatHeight(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHatHeight(value)
        }
    }

    fun addBodySize(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBodySize(value)
        }
    }

    fun addStatPoints(value: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStatPoints(value)
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPet(pet)
        }
    }

}