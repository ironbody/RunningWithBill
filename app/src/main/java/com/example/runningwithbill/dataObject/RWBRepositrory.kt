package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData

class RWBRepository(private val RWBDao: RWBDao) {

    val readAllData: LiveData<List<Pet>> = RWBDao.readAllData()
    val readLevel: LiveData<Int> = RWBDao.readLevel()

    suspend fun addPet(pet: Pet){
        RWBDao.addPet(pet)
    }

    suspend fun levelUp() {
        RWBDao.levelUp()
    }
    suspend fun changeExperience() {
        RWBDao.changeExperience()
    }
}