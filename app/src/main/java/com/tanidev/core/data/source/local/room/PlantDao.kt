package com.tanidev.core.data.source.local.room

import androidx.room.*
import com.tanidev.core.data.source.local.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plant")
    fun getAllPlant(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plant WHERE plantId =:plantId")
    fun getPlantById(plantId: Int): Flow<PlantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: List<PlantEntity>)

    @Update
    suspend fun updatePlant(plant: PlantEntity)

    @Query("UPDATE plant SET viewTotal =:viewTotal WHERE plantId =:plantId")
    fun updateView(viewTotal: Int, plantId: Int)

    @Query("SELECT * FROM plant ORDER BY viewTotal DESC LIMIT 2")
    fun getMostViewed(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plant WHERE plantName LIKE '%' || :queryString || '%' ")
    fun searchPlant(queryString: String?): Flow<List<PlantEntity>>

}