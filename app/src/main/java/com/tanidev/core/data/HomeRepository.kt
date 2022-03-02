package com.tanidev.core.data

import com.tanidev.core.data.source.local.DiseaseDataSource
import com.tanidev.core.data.source.local.PlantDataSource
import com.tanidev.core.data.source.local.ProductDataSource
import com.tanidev.core.data.source.remote.RemoteDataSource
import com.tanidev.core.domain.model.MostViewed
import com.tanidev.core.domain.model.SearchResult
import com.tanidev.core.domain.repository.IHomeRepository
import com.tanidev.core.utils.AppExecutors
import com.tanidev.core.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val plantDataSource: PlantDataSource,
    private val diseaseDataSource: DiseaseDataSource,
    private val productDataSource: ProductDataSource,
    private val appExecutors: AppExecutors
) : IHomeRepository {

    override fun getMostViewed(): Flow<List<MostViewed>> {

        val getListPlant = plantDataSource.getMostViewd().map {
            DataMapper.mapPlantEntitiesToMostViewed(it)
        }

        val getListDisease = diseaseDataSource.getMostViewd().map {
            DataMapper.mapDiseaseEntitiesToMostViewed(it)
        }

        val getListProduct = productDataSource.getMostViewd().map {
            DataMapper.mapProductEntitiesToMostViewed(it)
        }

        return flowOf(getListPlant, getListDisease, getListProduct)
                .flattenMerge(DEFAULT_CONCURRENCY)
    }

    override fun getSearchResult(queryString: String?): Flow<List<SearchResult>> {
        val getSearchPlantResult = plantDataSource.searchPlant(queryString).map {
            DataMapper.mapPlanEntitiesToSearchResult(it)
        }

        val getSearchDiseaseResult = diseaseDataSource.searchDisease(queryString).map {
            DataMapper.mapDiseaseEntitiesToSearchResult(it)
        }

        val getSearchProductResult = productDataSource.searchProduct(queryString).map {
            DataMapper.mapProductEntitiesToSearchResult(it)
        }

        return flowOf(getSearchPlantResult, getSearchDiseaseResult, getSearchProductResult)
                .flattenMerge(DEFAULT_CONCURRENCY)
    }

}