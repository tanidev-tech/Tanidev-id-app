package com.tanidev.core.domain.usecase

import com.tanidev.core.data.Resource
import com.tanidev.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun auth(phoneNo: String, password: String): Flow<Resource<User>>
    fun isLogin(): Flow<Boolean>
    fun register(name: String, phoneNo: String, region: String,
                 identificationNo: String, password: String) : Flow<Resource<Boolean>>
}