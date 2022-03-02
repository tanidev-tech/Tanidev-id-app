package com.tanidev.core.data

import com.tanidev.core.data.source.local.DiseaseDataSource
import com.tanidev.core.data.source.remote.RemoteDataSource
import com.tanidev.core.data.source.remote.network.ApiResponse
import com.tanidev.core.data.source.remote.request.DiseaseRequest
import com.tanidev.core.data.source.remote.request.PlantRequest
import com.tanidev.core.data.source.remote.response.DiseaseResponse
import com.tanidev.core.domain.model.Disease
import com.tanidev.core.domain.repository.IDiseaseRepository
import com.tanidev.core.utils.AppExecutors
import com.tanidev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiseaseRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val diseaseDataSource: DiseaseDataSource,
    private val appExecutors: AppExecutors
) : IDiseaseRepository {
    override fun getAllDisease(): Flow<Resource<List<Disease>>> =
        object : NetworkBoundResource<List<Disease>, List<DiseaseResponse>>() {
            override fun loadFromDb(): Flow<List<Disease>> {
                return diseaseDataSource.getAllDisease().map {
                    DataMapper.mapDiseaseEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Disease>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DiseaseResponse>>> =
                remoteDataSource.getAllDisease()

            override suspend fun saveCallresult(data: List<DiseaseResponse>) {
                val productList = DataMapper.mapDiseaseResponseToEntities(data)
                diseaseDataSource.insertAll(productList)
            }

        }.asFlow()

    override fun getDiseaseById(diseaseId: Int): Flow<Disease> {
        return diseaseDataSource.getDiseaseById(diseaseId).map {
            DataMapper.mapDiseaseEntityToDomain(it)
        }
    }

    override fun updateViewTotal(viewTotal: Int, plantId: Int) {
        appExecutors.diskIO().execute { diseaseDataSource.updateDiseaseView(viewTotal, plantId) }
    }

    override fun getDetailDisease(diseaseId: Int, diseaseName: String): Flow<Resource<Disease>> =
        object : NetworkBoundResource<Disease, List<DiseaseResponse>>() {
            override fun loadFromDb(): Flow<Disease> {
                return diseaseDataSource.getDiseaseById(diseaseId).map {
                    DataMapper.mapDiseaseEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Disease?): Boolean =
                data?.content.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DiseaseResponse>>> {
                val diseaseRequest = DiseaseRequest(diseaseName)
                return remoteDataSource.getDetailDisease(diseaseRequest)
            }

            override suspend fun saveCallresult(data: List<DiseaseResponse>) {
                val disease = DataMapper.mapDiseaseResponseToEntities(data)
                diseaseDataSource.updateDisease(disease.get(0))
            }

        }.asFlow()


}