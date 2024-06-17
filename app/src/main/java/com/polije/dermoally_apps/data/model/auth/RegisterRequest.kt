package com.polije.dermoally_apps.data.model.auth

import retrofit2.http.Field

data class RegisterRequest(
    @Field("name")
    val name: String,

    @Field("username")
    val username: String,

    @Field("email")
    val email: String,

    @Field("password")
    val password: String
)