package com.tanidev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiseaseResponse (
    @field:SerializedName("penyakitId")
    val diseaseId: Int,
    @field:SerializedName("namaPenyakit")
    val diseaseName: String,
    @field:SerializedName("jenisPenyakit")
    var diseaseType: String,
    @field:SerializedName("produkId")
    var productId: Int,
    @field:SerializedName("tanamanId")
    var plantId: Int,
    @field:SerializedName("content")
    var content: String,
    @field:SerializedName("createdDt")
    var createdDate: String,
    @field:SerializedName("viewTotal")
    var viewTotal: Int
)