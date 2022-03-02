package com.tanidev.core.di

import com.tanidev.core.data.*
import com.tanidev.core.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePlantRepository(plantRepository: PlantRepository) : IPlantRepository

    @Binds
    abstract fun provideDiseaseRepository(diseaseRepository: DiseaseRepository) : IDiseaseRepository

    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepository) : IProductRepository

    @Binds
    abstract fun provideHomeRepository(homeRepository: HomeRepository): IHomeRepository

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository): IUserRepository
}