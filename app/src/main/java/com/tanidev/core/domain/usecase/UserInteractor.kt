package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) : UserUseCase{
    override fun auth(phoneNo: String, password: String) = userRepository.auth(phoneNo, password)
    override fun isLogin(): Flow<Boolean> = userRepository.isLogin()
    override fun register(
        name: String,
        phoneNo: String,
        region: String,
        identificationNo: String,
        password: String
    ): Flow<Resource<Boolean>> = userRepository.register(
        name,
        phoneNo,
        region,
        identificationNo,
        password
    )

}