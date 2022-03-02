package com.tanidev.view.product

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.ProductUseCase

class ProductViewModel @ViewModelInject constructor(productUseCase: ProductUseCase) : ViewModel() {
    val product = productUseCase.getAllProduct().asLiveData()
}