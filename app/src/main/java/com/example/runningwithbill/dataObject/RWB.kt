package com.example.runningwithbill.dataObject

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "pet")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "1")
    val level: Int,
    @ColumnInfo(defaultValue = "0")
    val experience: Int,
    @ColumnInfo(defaultValue = "100")
    val health: Int,
    @ColumnInfo(defaultValue = "0")
    val food: Int,
    @ColumnInfo(defaultValue = "0")
    val beakLength: Int,
    @ColumnInfo(defaultValue = "0")
    val hatHeight: Int,
    @ColumnInfo(defaultValue = "0")
    val bodySize: Int,
    @ColumnInfo(defaultValue = "0")
    val statPoints: Int
)

@Entity(tableName = "stats")
data class Stats(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "0")
    val m: Int,
)

@Entity(tableName = "gps")
data class Gps(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
)