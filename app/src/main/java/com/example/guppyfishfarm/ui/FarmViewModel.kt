package com.example.guppyfishfarm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.guppyfishfarm.model.farm
import com.example.guppyfishfarm.repository.FarmRepository
import kotlinx.coroutines.launch

class FarmViewModel (private val repository: FarmRepository): ViewModel() {
    val allFarms: LiveData<List<farm>> = repository.allFarms.asLiveData()

    fun insert(farm: farm) = viewModelScope.launch {
        repository.insertFarm(farm)
    }

    fun delete(farm: farm) = viewModelScope.launch {
        repository.deleteFarm(farm)
    }

    fun update(farm: farm) = viewModelScope.launch {
        repository.updateFarm(farm)
    }
}

class FarmViewModelFactory(private val repository: FarmRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((FarmViewModel::class.java))) {
            return FarmViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}