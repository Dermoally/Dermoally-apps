package com.polije.dermoally_apps.data.model.auth

import retrofit2.http.Field

data class LoginRequest(
    @Field("username")
    val username: String,

    @Field("password")
    val password: String
)