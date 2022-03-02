package com.tanidev.core.domain.usecase

import com.tanidev.core.domain.repository.IDiseaseRepository
import javax.inject.Inject

class DiseaseInteractor @Inject constructor(private val diseaseRepository: IDiseaseRepository) : DiseaseUseCase{
    override fun getAllDisease() = diseaseRepository.getAllDisease()
    override fun getDiseaseById(diseaseId: Int) = diseaseRepository.getDiseaseById(diseaseId)
    override fun updateViewTotal(viewTotal: Int, plantId: Int) = diseaseRepository.updateViewTotal(viewTotal, plantId)
    override fun getDeatilDisease(diseaseId: Int, diseaseName: String) = diseaseRepository.getDetailDisease(diseaseId, diseaseName)

}