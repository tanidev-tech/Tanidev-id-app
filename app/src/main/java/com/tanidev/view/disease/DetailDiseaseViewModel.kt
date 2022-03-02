package com.tanidev.view.disease

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tanidev.core.domain.usecase.DiseaseUseCase

class DetailDiseaseViewModel @ViewModelInject constructor(private val diseaseUseCase: DiseaseUseCase) : ViewModel() {

    fun detailDesease(diseaseId: Int, diseaseName: String) = diseaseUseCase.getDeatilDisease(diseaseId, diseaseName).asLiveData()

    fun updateViewTotal(viewTotal: Int, plantId: Int) = diseaseUseCase.updateViewTotal(viewTotal, plantId)
}