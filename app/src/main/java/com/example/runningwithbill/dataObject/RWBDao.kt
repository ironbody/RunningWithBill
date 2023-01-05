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

    @Query("UPDATE pet SET health = (health + :value) WHERE id = 1")
    suspend fun addHealth(value: Int)

    @Query("UPDATE pet SET food = (food + :value) WHERE id = 1")
    suspend fun addFood(value: Int)

    @Query("UPDATE pet SET beakLength = (beakLength + :value) WHERE id = 1")
    suspend fun addBeakLength(value: Int)

    @Query("UPDATE pet SET hatHeight = (hatHeight + :value) WHERE id = 1")
    suspend fun addHatHeight(value: Int)

    @Query("UPDATE pet SET bodySize = (bodySize + :value) WHERE id = 1")
    suspend fun addBodySize(value: Int)

    @Query("UPDATE pet SET statPoints = (statPoints + :value) WHERE id = 1")
    suspend fun addStatPoints(value: Int)

    //Read functions
    @Query("SELECT * FROM pet ORDER BY id ASC")
    fun readAllData(): LiveData<List<Pet>>

    @Query("SELECT level FROM pet WHERE id = 1")
    fun readLevel(): LiveData<Int>

    @Query("SELECT experience FROM pet WHERE id = 1")
    fun readExperience(): LiveData<Int>

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