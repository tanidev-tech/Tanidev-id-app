package com.tanidev.view.disease

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.DiseaseUseCase

class DiseaseViewModel @ViewModelInject constructor(diseaseUseCase: DiseaseUseCase) : ViewModel() {
    val disease = diseaseUseCase.getAllDisease().asLiveData()
}