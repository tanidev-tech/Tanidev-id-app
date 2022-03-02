package com.tanidev.view.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tanidev.core.domain.usecase.HomeUseCase
import com.tanidev.core.domain.usecase.UserUseCase

class SplashViewModel @ViewModelInject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    val isLogin = userUseCase.isLogin().asLiveData()
}