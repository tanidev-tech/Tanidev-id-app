package com.tanidev.view.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tanidev.core.domain.usecase.HomeUseCase

class SearchViewModel @ViewModelInject constructor(homeUseCase: HomeUseCase) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)



    val searchResult  = currentQuery.switchMap { queryString ->
        homeUseCase.getSearchResult(queryString).asLiveData()
    }

    fun search(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "pupuk"
    }
}