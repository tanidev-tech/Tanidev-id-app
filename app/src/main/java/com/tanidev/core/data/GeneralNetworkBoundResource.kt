package com.tanidev.core.data

import com.tanidev.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class GeneralNetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    emitAll(isRegistrationSuccess().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun isRegistrationSuccess(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}