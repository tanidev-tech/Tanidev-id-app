package com.tanidev.core.data

import com.google.gson.Gson
import com.tanidev.core.data.source.remote.RemoteDataSource
import com.tanidev.core.data.source.remote.network.ApiResponse
import com.tanidev.core.data.source.remote.request.AuthRequest
import com.tanidev.core.data.source.remote.request.RegistrationRequest
import com.tanidev.core.data.source.remote.response.AuthResponse
import com.tanidev.core.data.source.remote.response.RegistrationResponse
import com.tanidev.core.domain.model.User
import com.tanidev.core.domain.repository.IUserRepository
import com.tanidev.core.utils.AppExecutors
import com.tanidev.core.utils.SessionManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sessionManager: SessionManager,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun auth(phoneNo: String, password: String) : Flow<Resource<User>> =
        object : AuthNetworkBoundResource<User, AuthResponse>() {
            override fun shouldAuth(): Boolean {
                return sessionManager.isLogin
            }

            override fun loadSession(): Flow<User> {
               val json = sessionManager.getFromPreference(SessionManager.KEY_USER)
               val user = Gson().fromJson<User>(json, User::class.java)
               return flowOf(user)
            }

            override suspend fun createCall(): Flow<ApiResponse<AuthResponse>> {
                val authRequest = AuthRequest(phoneNo, password)
                return remoteDataSource.auth(authRequest)
            }

            override suspend fun saveCallresult(data: AuthResponse) {
                val json = Gson().toJson(data.data)
               sessionManager.createLoginSession(json)
            }
        }.asFlow()

    override fun isLogin(): Flow<Boolean> {
        return flowOf(sessionManager.isLogin)
    }

    override fun register(
        name: String,
        phoneNo: String,
        region: String,
        identificationNo: String,
        password: String
    ): Flow<Resource<Boolean>> =
        object : GeneralNetworkBoundResource<Boolean, Boolean>() {
            override suspend fun createCall(): Flow<ApiResponse<Boolean>> {
                val registrationRequest = RegistrationRequest(name, identificationNo,
                    "30", region, phoneNo, password)
                return remoteDataSource.register(registrationRequest)
            }

            override suspend fun isRegistrationSuccess(): Flow<Boolean> {
                return flowOf(true)
            }
        }.asFlow()

}