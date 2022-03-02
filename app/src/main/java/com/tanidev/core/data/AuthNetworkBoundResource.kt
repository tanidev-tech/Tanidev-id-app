package com.tanidev.core.data

import com.tanidev.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class AuthNetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        if (!shouldAuth()) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallresult(apiResponse.data)
                    emitAll(loadSession().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadSession().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadSession().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun shouldAuth(): Boolean

    protected abstract fun loadSession(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallresult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}