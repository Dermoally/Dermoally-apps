package com.polije.dermoally_apps.data.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LoginResponse(
    @Json(name = "error")
    val error: Boolean,

    @Json(name = "message")
    val message: String,

    @Json(name = "token")
    val token: String?
)
