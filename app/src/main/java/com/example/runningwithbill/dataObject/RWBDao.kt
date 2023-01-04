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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStats(stats: Stats)

    @Query("UPDATE stats SET dayMeters = :metersDay WHERE id = 1")
    suspend fun setMetersDay(metersDay: Int)

    @Query("UPDATE stats SET weekMeters = :metersWeek WHERE id = 1")
    suspend fun setMetersWeek(metersWeek: Int)

    @Query("UPDATE stats SET monthMeters = :metersMonth WHERE id = 1")
    suspend fun setMetersMonth(metersMonth: Int)

    @Query("UPDATE stats SET totalMeters = :metersTotal WHERE id = 1")
    suspend fun setMetersTotal(metersTotal: Int)

    @Query("SELECT dayMeters FROM stats WHERE id = 1")
    fun readDay(): LiveData<Int>

    @Query("SELECT weekMeters FROM stats WHERE id = 1")
    fun readWeek(): LiveData<Int>

    @Query("SELECT monthMeters FROM stats WHERE id = 1")
    fun readMonth(): LiveData<Int>

    @Query("SELECT totalMeters FROM stats WHERE id = 1")
    fun readTotal(): LiveData<Int>
    // Gps

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGpsCords(gps: Gps)

}