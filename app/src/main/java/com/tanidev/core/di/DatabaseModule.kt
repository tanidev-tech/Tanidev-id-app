package com.tanidev.core.di

import android.content.Context
import androidx.room.Room
import com.tanidev.core.data.source.local.room.DiseaseDao
import com.tanidev.core.data.source.local.room.PlantDao
import com.tanidev.core.data.source.local.room.ProductDao
import com.tanidev.core.data.source.local.room.TaniDevDatabase
import com.tanidev.core.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : TaniDevDatabase = Room.databaseBuilder(
        context,
        TaniDevDatabase::class.java,
        "TaniDev.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePlantDao(database: TaniDevDatabase) : PlantDao = database.plantDao()

    @Provides
    fun provideDiseaseDao(database: TaniDevDatabase) : DiseaseDao = database.diseaseDao()

    @Provides
    fun provideProductDao(database: TaniDevDatabase) : ProductDao = database.productDao()

    @Provides
    fun provideSessionManager(@ApplicationContext context: Context) : SessionManager = SessionManager(context)
}