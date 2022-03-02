package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProduct(): Flow<Resource<List<Product>>>
    fun getProductById(productId: Int): Flow<Product>
    fun updateViewTotal(viewTotal: Int, plantId: Int)
    fun getDeatilProduct(productId: Int, productName: String): Flow<Resource<Product>>
}