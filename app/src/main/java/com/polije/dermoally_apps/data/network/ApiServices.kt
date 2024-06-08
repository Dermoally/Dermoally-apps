package com.polije.dermoally_apps.data.network

import com.polije.dermoally_apps.data.auth.LoginRequest
import com.polije.dermoally_apps.data.auth.LoginResponse
import com.polije.dermoally_apps.data.auth.RegisterRequest
import com.polije.dermoally_apps.data.auth.RegisterResponse
import com.polije.dermoally_apps.data.disease.HistoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("history")
    suspend fun getAllHistory(
    ): HistoryResponse

    @GET("favorite")
    suspend fun getAllFavorite(
    ): HistoryResponse

}