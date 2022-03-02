package com.tanidev.core.domain.repository

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Disease
import kotlinx.coroutines.flow.Flow

interface IDiseaseRepository {
    fun getAllDisease(): Flow<Resource<List<Disease>>>
    fun getDiseaseById(diseaseId: Int): Flow<Disease>
    fun updateViewTotal(viewTotal: Int, plantId: Int)
    fun getDetailDisease(diseaseId: Int, diseaseName: String): Flow<Resource<Disease>>
}