package com.tanidev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListDiseaseResponse (
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("data")
    val data: List<DiseaseResponse>,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("session")
    val session: String,
    @field:SerializedName("timeStamp")
    val timeStamp: String
)