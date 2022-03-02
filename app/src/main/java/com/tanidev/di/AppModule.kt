package com.tanidev.di

import com.tanidev.core.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providePlantUseCase(plantInteractor: PlantInteractor): PlantUseCase

    @Binds
    abstract fun provideDiseaseUseCase(diseaseInteractor: DiseaseInteractor): DiseaseUseCase

    @Binds
    abstract fun provideProductUseCase(productInteractor: ProductInteractor): ProductUseCase

    @Binds
    abstract fun provideHomeUseCase(homeInteractor: HomeInteractor): HomeUseCase

    @Binds
    abstract fun provideUserUserCase(userInteractor: UserInteractor): UserUseCase

}