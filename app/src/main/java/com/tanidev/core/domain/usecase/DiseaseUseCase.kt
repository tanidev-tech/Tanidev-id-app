package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Disease
import kotlinx.coroutines.flow.Flow

interface DiseaseUseCase {
    fun getAllDisease(): Flow<Resource<List<Disease>>>
    fun getDiseaseById(diseaseId: Int): Flow<Disease>
    fun updateViewTotal(viewTotal: Int, plantId: Int)
    fun getDeatilDisease(diseaseId: Int, diseaseName: String): Flow<Resource<Disease>>
}