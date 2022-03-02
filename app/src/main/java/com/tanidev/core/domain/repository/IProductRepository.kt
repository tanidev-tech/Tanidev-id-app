package com.tanidev.core.domain.repository

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getAllProduct(): Flow<Resource<List<Product>>>
    fun getProductById(productId: Int): Flow<Product>
    fun updateViewTotal(viewTotal: Int, plantId: Int)
    fun getDetailProduct(productId: Int, productName: String): Flow<Resource<Product>>
}