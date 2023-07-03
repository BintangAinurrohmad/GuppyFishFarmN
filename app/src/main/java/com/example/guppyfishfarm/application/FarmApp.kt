package com.example.guppyfishfarm.application

import android.app.Application
import com.example.guppyfishfarm.repository.FarmRepository

class FarmApp: Application() {
    val database by lazy { FarmDatabase.getDatabase(this) }
    val repository by lazy { FarmRepository(database.FarmDao()) }
}