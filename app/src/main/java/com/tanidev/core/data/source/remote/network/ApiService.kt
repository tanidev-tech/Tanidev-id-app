package com.tanidev.core.data.source.remote.network

import com.tanidev.core.data.source.remote.request.*
import com.tanidev.core.data.source.remote.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/tanaman/getAllTanaman")
    suspend fun getListPlant(): ListPlantResponse

    @POST("api/tanaman/getTanamanBySingleParam")
    suspend fun getDetailPlant(@Body plantName: PlantRequest): ListPlantResponse

    @GET("api/penyakit/getAllPenyakit")
    suspend fun getListDisease(): ListDiseaseResponse

    @POST("api/penyakit/getPenyakitBySingleParam")
    suspend fun getDeatilDisease(@Body diseaseName: DiseaseRequest): ListDiseaseResponse

    @GET("api/produk/getAllProduk")
    suspend fun getListProduct(): ListProductResponse

    @POST("api/produk/getProdukBySingleParam")
    suspend fun getDetailProduct(@Body productRequest: ProductRequest): ListProductResponse

    @POST("api/authentication/login")
    suspend fun login(@Body loginRequest: AuthRequest): AuthResponse

    @POST("api/authentication/register")
    suspend fun register(@Body registrationRequest: RegistrationRequest) : RegistrationResponse

}