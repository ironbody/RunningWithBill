package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RWBDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPet(pet: Pet)

    @Query("UPDATE pet SET level = (level + 1) WHERE id = 1")
    suspend fun levelUp()

    @Query("UPDATE pet SET experience = (experience + 0.5) WHERE id = 1")
    suspend fun changeExperience()

    @Query("SELECT * FROM pet ORDER BY id ASC")
    fun readAllData(): LiveData<List<Pet>>


    @Query("SELECT level FROM pet WHERE id = 1")
    fun readLevel(): LiveData<Int>


}