package com.tanidev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegistrationDataResponse (
    @field:SerializedName("email")
    val email: Int,
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