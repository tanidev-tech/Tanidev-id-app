package com.tanidev.view.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.MostViewed
import com.tanidev.core.domain.usecase.HomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(homeUseCase: HomeUseCase) : ViewModel() {

    val mostViewed = homeUseCase.getMostViewed().asLiveData()

}