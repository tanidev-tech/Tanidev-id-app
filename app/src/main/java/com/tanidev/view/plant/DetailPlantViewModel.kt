package com.tanidev.view.plant

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.PlantUseCase

class DetailPlantViewModel @ViewModelInject constructor(private val plantUseCase: PlantUseCase) : ViewModel() {

    fun detailPlant(plantId: Int, plantName: String) = plantUseCase.getDeatilPlant(plantId, plantName).asLiveData()

    fun updateViewTotal(viewTotal: Int, plantId: Int) = plantUseCase.updateViewTotal(viewTotal, plantId)
}