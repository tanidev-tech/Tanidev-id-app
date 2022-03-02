package com.tanidev.core.data.source.local

import com.tanidev.core.data.source.local.entity.DiseasEntity
import com.tanidev.core.data.source.local.entity.PlantEntity
import com.tanidev.core.data.source.local.entity.ProductEntity
import com.tanidev.core.data.source.local.room.DiseaseDao
import com.tanidev.core.data.source.local.room.PlantDao
import com.tanidev.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val plantDao: PlantDao,
    private val diseaseDao: DiseaseDao,
    private val productDao: ProductDao
) {
    fun getAllPlant() : Flow<List<PlantEntity>> = plantDao.getAllPlant()
    fun getPlantById(plantId: Int) : Flow<PlantEntity> = plantDao.getPlantById(plantId)
    fun updatePlantView(viewTotal: Int, plantId: Int) = plantDao.updateView(viewTotal, plantId)

    fun getAllDisease() : Flow<List<DiseasEntity>> = diseaseDao.getAllDisease()
    fun getDiseaseById(diseaseId: Int) : Flow<DiseasEntity> = diseaseDao.getDiseaseById(diseaseId)
    fun updateDiseaseView(viewTotal: Int, plantId: Int) = diseaseDao.updateView(viewTotal, plantId)

    fun getAllProduct() : Flow<List<ProductEntity>> = productDao.getAllProduct()
    fun getProductById(productId: Int) : Flow<ProductEntity> = productDao.getProductById(productId)
    fun updateProductView(viewTotal: Int, plantId: Int) = productDao.updateView(viewTotal, plantId)
}