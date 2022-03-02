package com.tanidev.core.data

import com.tanidev.core.data.source.local.ProductDataSource
import com.tanidev.core.data.source.remote.RemoteDataSource
import com.tanidev.core.data.source.remote.network.ApiResponse
import com.tanidev.core.data.source.remote.request.PlantRequest
import com.tanidev.core.data.source.remote.request.ProductRequest
import com.tanidev.core.data.source.remote.response.PlantResponse
import com.tanidev.core.data.source.remote.response.ProductResponse
import com.tanidev.core.domain.model.Plant
import com.tanidev.core.domain.model.Product
import com.tanidev.core.domain.repository.IProductRepository
import com.tanidev.core.utils.AppExecutors
import com.tanidev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val productDataSource: ProductDataSource,
    private val appExecutors: AppExecutors
) : IProductRepository {
    override fun getAllProduct(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<ProductResponse>>() {
            override fun loadFromDb(): Flow<List<Product>> {
                return productDataSource.getAllProduct().map {
                    DataMapper.mapProductEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Product>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponse>>> =
                remoteDataSource.getAllProduct()

            override suspend fun saveCallresult(data: List<ProductResponse>) {
                val productList = DataMapper.mapProductResponseToEntities(data)
                productDataSource.insertProduct(productList)
            }

        }.asFlow()

    override fun getProductById(productId: Int): Flow<Product> {
        return productDataSource.getProductById(productId).map {
            DataMapper.mapProductEntityToDomain(it)
        }
    }

    override fun updateViewTotal(viewTotal: Int, plantId: Int) {
        appExecutors.diskIO().execute { productDataSource.updateProductView(viewTotal, plantId) }
    }

    override fun getDetailProduct(productId: Int, productName: String): Flow<Resource<Product>> =
        object : NetworkBoundResource<Product, List<ProductResponse>>() {
            override fun loadFromDb(): Flow<Product> {
                return productDataSource.getProductById(productId).map {
                    DataMapper.mapProductEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Product?): Boolean =
                data?.content.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponse>>> {
                val productRequest = ProductRequest(productName)
                return remoteDataSource.getDetailProduct(productRequest)
            }

            override suspend fun saveCallresult(data: List<ProductResponse>) {
                val product = DataMapper.mapProductResponseToEntities(data)
                productDataSource.updateProduct(product.get(0))
            }

        }.asFlow()

}