package com.tanidev.core.data

import com.tanidev.core.data.source.local.PlantDataSource
import com.tanidev.core.data.source.remote.RemoteDataSource
import com.tanidev.core.data.source.remote.network.ApiResponse
import com.tanidev.core.data.source.remote.request.PlantRequest
import com.tanidev.core.data.source.remote.response.PlantResponse
import com.tanidev.core.domain.model.Plant
import com.tanidev.core.domain.repository.IPlantRepository
import com.tanidev.core.utils.AppExecutors
import com.tanidev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val plantDataSource: PlantDataSource,
    private val appExecutors: AppExecutors
) : IPlantRepository {
    override fun getAllPlant(): Flow<Resource<List<Plant>>> =
        object : NetworkBoundResource<List<Plant>, List<PlantResponse>>() {
            override fun loadFromDb(): Flow<List<Plant>> {
                return plantDataSource.getAllPlant().map {
                    DataMapper.mapPlantEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Plant>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PlantResponse>>> =
                remoteDataSource.getAllPlant()

            override suspend fun saveCallresult(data: List<PlantResponse>) {
                val plantList = DataMapper.mapPlantResponseToEntities(data)
                plantDataSource.insertPlants(plantList)
            }

        }.asFlow()

    override fun getPlantById(plantId: Int): Flow<Plant> {
        return plantDataSource.getPlantById(plantId).map {
            DataMapper.mapPlantEntityToDomain(it)
        }
    }

    override fun updateViewTotal(viewTotal: Int, plantId: Int) {
        appExecutors.diskIO().execute { plantDataSource.updatePlantView(viewTotal, plantId) }
    }

    override fun getDetailPlant(plantId: Int, plantName: String): Flow<Resource<Plant>> =
        object : NetworkBoundResource<Plant, List<PlantResponse>>() {
            override fun loadFromDb(): Flow<Plant> {
                return plantDataSource.getPlantById(plantId).map {
                    DataMapper.mapPlantEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Plant?): Boolean =
                data?.content.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PlantResponse>>> {
                val diseaseRequest = PlantRequest(plantName)
                return remoteDataSource.getDetailPlant(diseaseRequest)
            }

            override suspend fun saveCallresult(data: List<PlantResponse>) {
                val disease = DataMapper.mapPlantResponseToEntities(data)
                plantDataSource.updatePlant(disease.get(0))
            }

        }.asFlow()

}