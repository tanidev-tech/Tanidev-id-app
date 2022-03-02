package com.tanidev.core.data.source.local

import com.tanidev.core.data.source.local.entity.DiseasEntity
import com.tanidev.core.data.source.local.room.DiseaseDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiseaseDataSource @Inject constructor(private val diseaseDao: DiseaseDao) {
    fun getAllDisease() : Flow<List<DiseasEntity>> = diseaseDao.getAllDisease()
    fun getDiseaseById(diseaseId: Int) : Flow<DiseasEntity> = diseaseDao.getDiseaseById(diseaseId)
    suspend fun insertAll(diseaseList: List<DiseasEntity>) = diseaseDao.insertDisease(diseaseList)
    fun updateDiseaseView(viewTotal: Int, plantId: Int) = diseaseDao.updateView(viewTotal, plantId)
    fun getMostViewd() : Flow<List<DiseasEntity>> = diseaseDao.getMostViewed()
    fun searchDisease(queryString: String?) : Flow<List<DiseasEntity>> = diseaseDao.searchDisease(queryString)
    suspend fun updateDisease(disease: DiseasEntity) = diseaseDao.updateDisease(disease)
}