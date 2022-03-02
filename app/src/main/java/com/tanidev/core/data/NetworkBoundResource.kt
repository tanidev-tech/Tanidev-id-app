package com.tanidev.core.data

import com.tanidev.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallresult(apiResponse.data)
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallresult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}