package com.tanidev.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "productId")
    var productId: Int,

    @ColumnInfo(name = "productName")
    var productName: String,

    @ColumnInfo(name = "createdDate")
    var createdDate: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "imageId")
    var imageId: Int,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "imageThumbNail")
    var imageThumbNail: String,

    @ColumnInfo(name = "productType")
    var productType: String,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "viewTotal")
    var viewTotal: Int
)