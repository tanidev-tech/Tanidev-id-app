package com.tanidev.core.data.source.local.room

import androidx.room.*
import com.tanidev.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE productId =:productId")
    fun getProductById(productId: Int): Flow<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Query("UPDATE product SET viewTotal =:viewTotal WHERE productId =:productId")
    fun updateView(viewTotal: Int, productId: Int)

    @Query("SELECT * FROM product ORDER BY viewTotal DESC LIMIT 2")
    fun getMostViewed(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE productName LIKE '%' || :queryString || '%'")
    fun searchProduct(queryString: String?): Flow<List<ProductEntity>>

}