package com.example.runningwithbill.dataObject

import  androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pet::class, Gps::class], version = 1, exportSchema = false)
abstract class RWBDatabase : RoomDatabase(){



    abstract fun RWBDao(): RWBDao

    companion object{
        @Volatile
        private var INSTANCE: RWBDatabase? = null

        fun getDatabase(context: Context): RWBDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RWBDatabase::class.java,
                    "rwb_database"
                ).createFromAsset("database/rwb_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}