package com.tanidev.core.domain.usecase

import com.tanidev.core.domain.model.MostViewed
import com.tanidev.core.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getMostViewed(): Flow<List<MostViewed>>
    fun getSearchResult(queryString: String?): Flow<List<SearchResult>>
}