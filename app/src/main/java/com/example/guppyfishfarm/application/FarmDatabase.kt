package com.example.guppyfishfarm.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guppyfishfarm.dao.FarmDao
import com.example.guppyfishfarm.model.farm

@Database(entities = [farm::class], version = 1, exportSchema = false)
abstract class FarmDatabase: RoomDatabase() {
    abstract fun FarmDao(): FarmDao

    companion object{
        private var INSTANCE: FarmDatabase? = null

        fun getDatabase(context: Context): FarmDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmDatabase::class.java,
                    "farm_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}