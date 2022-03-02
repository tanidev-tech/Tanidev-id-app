package com.tanidev.core.domain.usecase

import com.tanidev.core.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val homeRepository: IHomeRepository) : HomeUseCase{
    override fun getMostViewed() = homeRepository.getMostViewed()
    override fun getSearchResult(queryString: String?) = homeRepository.getSearchResult(queryString)


}