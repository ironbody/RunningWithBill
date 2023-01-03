package com.example.runningwithbill.dataObject

import androidx.lifecycle.LiveData
import androidx.room.*

// This file could also be made into tree files
@Dao
interface RWBDao {

    // Pet

    //Write functions
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPet(pet: Pet)

    @Query("UPDATE pet SET level = (level + 1) WHERE id = 1")
    suspend fun levelUp()

    @Query("UPDATE pet SET experience = (experience + :xp) WHERE id = 1")
    suspend fun addExperience(xp: Int)

    //Read functions
    @Query("SELECT * FROM pet ORDER BY id ASC")
    fun readAllData(): LiveData<List<Pet>>

    @Query("SELECT level FROM pet WHERE id = 1")
    fun readLevel(): LiveData<Int>

    @Query("SELECT experience FROM pet WHERE id = 1")
    fun readExperience(): LiveData<Float>

    @Query("SELECT health FROM pet WHERE id = 1")
    fun readHealth(): LiveData<Int>

    @Query("SELECT food FROM pet WHERE id = 1")
    fun readFood(): LiveData<Int>

    @Query("SELECT beakLength FROM pet WHERE id = 1")
    fun readBeakLength(): LiveData<Int>

    @Query("SELECT hatHeight FROM pet WHERE id = 1")
    fun readHatHeight(): LiveData<Int>

    @Query("SELECT bodySize FROM pet WHERE id = 1")
    fun readBodySize(): LiveData<Int>

    @Query("SELECT statPoints FROM pet WHERE id = 1")
    fun readStatPoints(): LiveData<Int>

    // Stats

    // Gps

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGpsCords(gps: Gps)

}