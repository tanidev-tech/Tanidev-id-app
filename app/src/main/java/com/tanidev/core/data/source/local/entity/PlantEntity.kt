package com.tanidev.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class PlantEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "plantId")
    var plantId: Int,

    @ColumnInfo(name = "plantName")
    var plantName: String,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "light")
    var light: String,

    @ColumnInfo(name = "createdDate")
    var createdDate: String,

    @ColumnInfo(name = "plantType")
    var plantType: String,

    @ColumnInfo(name = "kalium")
    var kalium: String,

    @ColumnInfo(name = "waterContent")
    var waterContent: String,

    @ColumnInfo(name = "humidity")
    var humidity: String,

    @ColumnInfo(name = "sodium")
    var sodium: String,

    @ColumnInfo(name = "potassium")
    var potassium: String,

    @ColumnInfo(name = "temperature")
    var temperature: String,

    @ColumnInfo(name = "diseaseId")
    var diseaseId: String,

    @ColumnInfo(name = "updatedTimestamp")
    var updatedTimestamp: String,

    @ColumnInfo(name = "viewTotal")
    var viewTotal: Int

)