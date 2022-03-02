package com.tanidev.view.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tanidev.core.domain.usecase.HomeUseCase
import com.tanidev.core.domain.usecase.UserUseCase

class LoginViewModel @ViewModelInject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    fun login(phoneNo: String, password: String) = userUseCase.auth(phoneNo, password).asLiveData()
}