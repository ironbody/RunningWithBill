package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData
import androidx.room.*

// This file could also be made into tree files
@Dao
interface RWBDao {

    // Pet

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPet(pet: Pet)

    @Query("UPDATE pet SET level = (level + 1) WHERE id = 1")
    suspend fun levelUp()

    @Query("UPDATE pet SET experience = (experience + :xp) WHERE id = 1")
    suspend fun addExperience(xp: Int)

    @Query("SELECT * FROM pet ORDER BY id ASC")
    fun readAllData(): LiveData<List<Pet>>


    @Query("SELECT level FROM pet WHERE id = 1")
    fun readLevel(): LiveData<Int>
    // Stats

    // Gps

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGpsCords(gps: Gps)

}