package com.example.guppyfishfarm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.guppyfishfarm.model.farm
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmDao {
    @Query("SELECT * FROM farm_table ORDER BY name ASC")
    fun getAllFarm(): Flow<List<farm>>

    @Insert
    suspend fun insertFarm(farm: farm)

    @Delete
    suspend fun deleteFarm(farm: farm)

    @Update
    suspend fun updateFarm(farm: farm)
}