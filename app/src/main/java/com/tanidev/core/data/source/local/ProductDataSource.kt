package com.tanidev.core.data.source.local

import com.tanidev.core.data.source.local.entity.ProductEntity
import com.tanidev.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDataSource @Inject constructor(private val productDao: ProductDao) {
    fun getAllProduct() : Flow<List<ProductEntity>> = productDao.getAllProduct()
    fun getProductById(productId: Int) : Flow<ProductEntity> = productDao.getProductById(productId)
    suspend fun insertProduct(productList: List<ProductEntity>) = productDao.insertProduct(productList)
    fun updateProductView(viewTotal: Int, plantId: Int) = productDao.updateView(viewTotal, plantId)
    fun getMostViewd() : Flow<List<ProductEntity>> = productDao.getMostViewed()
    fun searchProduct(queryString: String?) : Flow<List<ProductEntity>> = productDao.searchProduct(queryString)
    suspend fun updateProduct(product: ProductEntity) = productDao.updateProduct(product)
}