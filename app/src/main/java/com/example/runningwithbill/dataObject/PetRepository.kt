package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData

class PetRepository(private val RWBDao: RWBDao) {

    val readAllData: LiveData<List<Pet>> = RWBDao.readAllData()
    val readLevel: LiveData<Int> = RWBDao.readLevel()
    val readExperience: LiveData<Int> = RWBDao.readExperience();
    val readHealth: LiveData<Int> = RWBDao.readHealth();
    val readFood: LiveData<Int> = RWBDao.readFood();
    val readBeakLength: LiveData<Int> = RWBDao.readBeakLength();
    val readHatHeight: LiveData<Int> = RWBDao.readHatHeight();
    val readBodySize: LiveData<Int> = RWBDao.readBodySize();
    val readStatPoints: LiveData<Int> = RWBDao.readStatPoints();

    suspend fun addPet(pet: Pet){
        RWBDao.addPet(pet)
    }
    suspend fun levelUp() {
        RWBDao.levelUp()
    }
    suspend fun addExperience(xp: Int) {
        RWBDao.addExperience(xp)
    }
    suspend fun addHealth(value: Int) {
        RWBDao.addHealth(value)
    }
    suspend fun addFood(value: Int) {
        RWBDao.addFood(value)
    }
    suspend fun addBeakLength(value: Int) {
        RWBDao.addBeakLength(value)
    }
    suspend fun addHatHeight(value: Int) {
        RWBDao.addHatHeight(value)
    }
    suspend fun addBodySize(value: Int) {
        RWBDao.addBodySize(value)
    }
    suspend fun addStatPoints(value: Int) {
        RWBDao.addStatPoints(value)
    }


}