package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Plant
import com.tanidev.core.domain.repository.IPlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantInteractor @Inject constructor(private val plantRepository: IPlantRepository) : PlantUseCase{
    override fun getAllPlant() = plantRepository.getAllPlant()
    override fun getPlantById(plantId: Int) = plantRepository.getPlantById(plantId)
    override fun updateViewTotal(viewTotal: Int, plantId: Int) = plantRepository.updateViewTotal(viewTotal, plantId)
    override fun getDeatilPlant(plantId: Int, plantName: String) = plantRepository.getDetailPlant(plantId, plantName)
}