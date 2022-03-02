package com.tanidev.core.data.source.remote

import android.util.Log
import com.tanidev.core.data.source.remote.network.ApiResponse
import com.tanidev.core.data.source.remote.network.ApiService
import com.tanidev.core.data.source.remote.request.*
import com.tanidev.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import java.util.function.BinaryOperator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPlant(): Flow<ApiResponse<List<PlantResponse>>> {
        return flow {
            try {
                val response = apiService.getListPlant()
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailPlant(plantRequest: PlantRequest): Flow<ApiResponse<List<PlantResponse>>> {
        return flow {
            try {
                val response = apiService.getDetailPlant(plantRequest)
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllDisease(): Flow<ApiResponse<List<DiseaseResponse>>> {
        return flow {
            try {
                val response = apiService.getListDisease()
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailDisease(diseaseRequest: DiseaseRequest): Flow<ApiResponse<List<DiseaseResponse>>> {
        return flow {
            try {
                val response = apiService.getDeatilDisease(diseaseRequest)
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllProduct(): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getListProduct()
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailProduct(productRequest: ProductRequest): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getDetailProduct(productRequest)
                val dataArr = response.data
                if (dataArr.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun auth(loginRequest: AuthRequest): Flow<ApiResponse<AuthResponse>> {
        return flow {
            try {
                val response = apiService.login(loginRequest)
                if (response.code == 200) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun register(registrationRequest: RegistrationRequest) : Flow<ApiResponse<Boolean>> {
        return flow {
            try {
                val response = apiService.register(registrationRequest)
                if (response.code == 200) {
                    emit(ApiResponse.Success(true))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}