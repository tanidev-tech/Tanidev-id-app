package com.tanidev.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class PlantRequest (
    @field:SerializedName("namaTanaman")
    val diseaseName: String
)