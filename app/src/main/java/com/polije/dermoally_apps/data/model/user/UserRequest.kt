package com.polije.dermoally_apps.data.model.user

import retrofit2.http.Field

data class UserRequest (
    @Field("email")
    val email: String,

    @Field("name")
    val name: String,

    @Field("profile_image_url")
    val imageProfile: String
)