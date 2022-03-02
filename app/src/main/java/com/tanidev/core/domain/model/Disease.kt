package com.tanidev.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Disease (
    val diseaseId: Int,
    val diseaseName: String,
    val diseaseType: String,
    val productId: Int,
    val plantId: Int,
    val content: String,
    val createdDate: String,
    val viewTotal: Int = 0

//    val generalInfo: String,
//    val counterMeasureInfo: String,
//    val preventionInfo: String,
//    var videos: List<>
//    var vounterMeasureSuggestion: List<>
//    var preventionSuggestion: List<>
): Parcelable