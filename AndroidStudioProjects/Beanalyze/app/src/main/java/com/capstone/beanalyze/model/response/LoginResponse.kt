package com.capstone.beanalyze.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val message: String,
    val token: String,
    val username: String,
    val password: String,
) : Parcelable