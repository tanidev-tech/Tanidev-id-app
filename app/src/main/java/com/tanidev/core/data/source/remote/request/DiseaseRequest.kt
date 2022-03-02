package com.tanidev.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class DiseaseRequest (
    @field:SerializedName("namaPenyakit")
    val diseaseName: String
)