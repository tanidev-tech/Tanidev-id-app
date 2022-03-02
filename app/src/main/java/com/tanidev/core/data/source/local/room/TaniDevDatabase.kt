package com.tanidev.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tanidev.core.data.source.local.entity.DiseasEntity
import com.tanidev.core.data.source.local.entity.PlantEntity
import com.tanidev.core.data.source.local.entity.ProductEntity

@Database(entities = [
    PlantEntity::class,
    DiseasEntity::class,
    ProductEntity::class],
    version = 4,
    exportSchema = false)
abstract class TaniDevDatabase : RoomDatabase() {

    abstract fun plantDao() : PlantDao

    abstract fun diseaseDao() : DiseaseDao

    abstract fun productDao() : ProductDao
}