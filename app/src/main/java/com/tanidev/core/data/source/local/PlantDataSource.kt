package com.tanidev.core.data.source.local

import com.tanidev.core.data.source.local.entity.PlantEntity
import com.tanidev.core.data.source.local.room.PlantDao
import com.tanidev.core.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantDataSource @Inject constructor(private val plantDao: PlantDao) {
    fun getAllPlant() : Flow<List<PlantEntity>> = plantDao.getAllPlant()
    fun getPlantById(plantId: Int) : Flow<PlantEntity> = plantDao.getPlantById(plantId)
    suspend fun insertPlants(plantList: List<PlantEntity>) = plantDao.insertPlant(plantList)
    fun updatePlantView(viewTotal: Int, plantId: Int) = plantDao.updateView(viewTotal, plantId)
    fun getMostViewd() : Flow<List<PlantEntity>> = plantDao.getMostViewed()
    fun searchPlant(queryString: String?) : Flow<List<PlantEntity>> = plantDao.searchPlant(queryString)
    suspend fun updatePlant(plant: PlantEntity) = plantDao.updatePlant(plant)
}