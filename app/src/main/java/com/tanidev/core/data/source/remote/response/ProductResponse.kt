package com.tanidev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @field:SerializedName("produkId")
    val productId: Int,
    @field:SerializedName("namaProduk")
    val productName: String,
    @field:SerializedName("createdDt")
    var createdDate: String,
    @field:SerializedName("description")
    var description: String,
    @field:SerializedName("imageId")
    var imageId: Int,
    @field:SerializedName("image")
    var image: String,
    @field:SerializedName("imageThumbNail")
    var imageThumbNail: String,
    @field:SerializedName("jenisProduk")
    var productType: String,
    @field:SerializedName("content")
    var content: String,
    @field:SerializedName("viewTotal")
    var viewTotal: Int
)