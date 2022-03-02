package com.tanidev.core.data.source.local.room

import androidx.room.*
import com.tanidev.core.data.source.local.entity.DiseasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {

    @Query("SELECT * FROM disease")
    fun getAllDisease(): Flow<List<DiseasEntity>>

    @Query("SELECT * FROM disease WHERE diseaseId =:diseaseId")
    fun getDiseaseById(diseaseId: Int): Flow<DiseasEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisease(disease: List<DiseasEntity>)

    @Update
    suspend fun updateDisease(disease: DiseasEntity)

    @Query("UPDATE disease SET viewTotal =:viewTotal WHERE diseaseId =:diseaseId")
    fun updateView(viewTotal: Int, diseaseId: Int)

    @Query("SELECT * FROM disease ORDER BY viewTotal DESC LIMIT 2")
    fun getMostViewed(): Flow<List<DiseasEntity>>

    @Query("SELECT * FROM disease WHERE diseaseName LIKE '%' || :queryString || '%'")
    fun searchDisease(queryString: String?): Flow<List<DiseasEntity>>

}