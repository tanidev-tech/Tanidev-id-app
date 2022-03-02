package com.tanidev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("authentication_id")
    val authenticationId: Int,
    @field:SerializedName("created_dt")
    val createdDate: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("nama")
    val name: String,
    @field:SerializedName("nik")
    val identificationCardNo: String,
    @field:SerializedName("nomor_hp")
    val phoneNumber: String,
    @field:SerializedName("pengawas_id")
    val spvId: Int,
    @field:SerializedName("petani_id")
    val farmerId: String,
    @field:SerializedName("usia")
    val age: Int
)