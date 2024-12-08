package com.capstone.beanalyze.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassificationResponse(
    val message: String,
    val disease_name: String,
    val confident: Double,
    val image_predict_link: String,
    val image_disease_link: String,
    val impact: String,
    val cause: String,
    val identification: String,
    val solution: String,
    val date: String
) : Parcelable