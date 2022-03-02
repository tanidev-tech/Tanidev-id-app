package com.tanidev.view.plant

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.PlantUseCase

class PlantViewModel @ViewModelInject constructor(plantUseCase: PlantUseCase) : ViewModel() {
    val plant = plantUseCase.getAllPlant().asLiveData()
}