package com.example.runningwithbill.dataObject

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.*

@Entity(tableName = "pet")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "1")
    val level: Int,
    @ColumnInfo(defaultValue = "0")
    val experience: Float,
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
    val dayMeters: Int,
    @ColumnInfo(defaultValue = "0")
    val weekMeters: Int,
    @ColumnInfo(defaultValue = "0")
    val monthMeters: Int,
    @ColumnInfo(defaultValue = "0")
    val totalMeters: Int,

    )

@Entity(tableName = "gps")
data class Gps(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
)