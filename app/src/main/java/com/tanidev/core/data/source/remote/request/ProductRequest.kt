package com.tanidev.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class ProductRequest (
    @field:SerializedName("namaProduk")
    val productName: String
)