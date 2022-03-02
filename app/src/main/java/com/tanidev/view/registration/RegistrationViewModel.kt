package com.tanidev.view.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tanidev.core.domain.usecase.HomeUseCase
import com.tanidev.core.domain.usecase.UserUseCase

class RegistrationViewModel @ViewModelInject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    fun register(name: String, phoneNo: String,
                 region: String, identificationNo: String,
                 password: String) =
        userUseCase.register(name, phoneNo, region, identificationNo, password).asLiveData()
}