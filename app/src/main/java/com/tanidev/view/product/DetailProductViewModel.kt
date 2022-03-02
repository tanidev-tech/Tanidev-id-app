package com.tanidev.view.product

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.ProductUseCase

class DetailProductViewModel @ViewModelInject constructor(private val productUseCase: ProductUseCase) : ViewModel() {

    fun detailProduct(productId: Int, productName: String) = productUseCase.getDeatilProduct(productId, productName).asLiveData()

    fun updateViewTotal(viewTotal: Int, productId: Int) = productUseCase.updateViewTotal(viewTotal, productId)
}