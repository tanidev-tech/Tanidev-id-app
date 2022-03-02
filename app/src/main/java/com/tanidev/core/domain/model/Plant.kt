package com.tanidev.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plant (
    val plantId: Int,
    val plantName: String,
    val content: String,
    val light: String,
    val createdDate: String,
    val plantType: String,
    val kalium: String,
    val waterContent: String,
    val humidity: String,
    val sodium: String,
    val potassium: String,
    val temperature: String,
    val diseaseId: String,
    val updatedTimestamp: String,
    val viewTotal: Int
): Parcelable