package com.tanidev.core.domain.usecase

import com.tanidev.core.domain.repository.IProductRepository
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val productRepository: IProductRepository) : ProductUseCase{
    override fun getAllProduct() = productRepository.getAllProduct()
    override fun getProductById(productId: Int) = productRepository.getProductById(productId)
    override fun updateViewTotal(viewTotal: Int, plantId: Int) = productRepository.updateViewTotal(viewTotal, plantId)
    override fun getDeatilProduct(productId: Int, productName: String) = productRepository.getDetailProduct(productId, productName)

}