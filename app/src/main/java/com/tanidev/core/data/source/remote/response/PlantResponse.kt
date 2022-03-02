package com.tanidev.core.data.source.remote.response

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PlantResponse (
    @field:SerializedName("tanamanId")
    var plantId: Int,

    @field:SerializedName("namaTanaman")
    var plantName: String,

    @field:SerializedName("content")
    var content: String,

    @ColumnInfo(name = "cahaya")
    var light: String,

    @ColumnInfo(name = "createdDt")
    var createdDate: String,

    @ColumnInfo(name = "jenisTanaman")
    var plantType: String,

    @ColumnInfo(name = "kalium")
    var kalium: String,

    @ColumnInfo(name = "kandunganAir")
    var waterContent: String,

    @ColumnInfo(name = "kelembapanUdara")
    var humidity: String,

    @ColumnInfo(name = "natrium")
    var sodium: String,

    @ColumnInfo(name = "potasium")
    var potassium: String,

    @ColumnInfo(name = "suhu")
    var temperature: String,

    @ColumnInfo(name = "penyakitId")
    var diseaseId: String,

    @ColumnInfo(name = "updatedTimestamp")
    var updatedTimestamp: String,

    @field:SerializedName("viewTotal")
    var viewTotal: Int
)