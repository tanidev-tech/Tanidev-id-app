package com.tanidev.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @field:SerializedName("nomorHp")
    val phoneNo: String,
    @field:SerializedName("password")
    val password: String
)