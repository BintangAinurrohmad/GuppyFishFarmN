package com.example.guppyfishfarm.repository

import com.example.guppyfishfarm.dao.FarmDao
import com.example.guppyfishfarm.model.farm
import kotlinx.coroutines.flow.Flow

class FarmRepository(private val farmDao: FarmDao) {
    val allFarms: Flow<List<farm>> = farmDao.getAllFarm()

    suspend fun insertFarm(farm: farm) {
        farmDao.insertFarm(farm)
    }
    suspend fun deleteFarm(farm: farm) {
        farmDao.deleteFarm(farm)
    }
    suspend fun updateFarm(farm: farm) {
        farmDao.updateFarm(farm)
    }
}