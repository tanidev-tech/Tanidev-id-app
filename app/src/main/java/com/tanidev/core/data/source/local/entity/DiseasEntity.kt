package com.tanidev.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disease")
data class DiseasEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "diseaseId")
    var diseaseId: Int,

    @ColumnInfo(name = "diseaseName")
    var diseaseName: String,

    @ColumnInfo(name = "diseaseType")
    var diseaseType: String,

    @ColumnInfo(name = "productId")
    var productId: Int,

    @ColumnInfo(name = "plantId")
    var plantId: Int,

    @ColumnInfo(name = "content")
    var content: String = "",

    @ColumnInfo(name = "createdDate")
    var createdDate: String,

    @ColumnInfo(name = "viewTotal")
    var viewTotal: Int

)