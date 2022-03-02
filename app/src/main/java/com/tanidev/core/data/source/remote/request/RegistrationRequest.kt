package com.tanidev.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class RegistrationRequest (
    @field:SerializedName("nama")
    val name: String,
    @field:SerializedName("nik")
    val identificationNo: String,
    @field:SerializedName("usia")
    val age: String,
    @field:SerializedName("kota")
    val region: String,
    @field:SerializedName("nomorHp")
    val phoneNo: String,
    @field:SerializedName("password")
    val password: String
)