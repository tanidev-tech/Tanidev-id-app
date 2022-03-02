package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Disease
import com.tanidev.core.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantUseCase {
    fun getAllPlant(): Flow<Resource<List<Plant>>>
    fun getPlantById(plantId: Int): Flow<Plant>
    fun updateViewTotal(viewTotal: Int, plantId: Int)
    fun getDeatilPlant(plantId: Int, plantName: String): Flow<Resource<Plant>>
}