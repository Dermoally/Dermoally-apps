package com.polije.dermoally_apps.data.model

data class User(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)