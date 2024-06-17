package com.polije.dermoally_apps.data.model.user

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "email")
    val email: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "profile_image_url")
    val profileImageUrl: String?,

    @Json(name = "username")
    val username: String?,
)
