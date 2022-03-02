package com.tanidev.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    val productId: Int,
    val productName: String,
    val createdDate: String,
    val description: String,
    val imageId: Int,
    val image: String,
    val imageThumbNail: String,
    val productType: String,
    val content: String,
    val viewTotal: Int
): Parcelable