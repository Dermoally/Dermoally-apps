package com.polije.dermoally_apps.data.network

import com.polije.dermoally_apps.data.auth.LoginRequest
import com.polije.dermoally_apps.data.auth.LoginResponse
import com.polije.dermoally_apps.data.auth.RegisterRequest
import com.polije.dermoally_apps.data.auth.RegisterResponse
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.disease.HistoryResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServices {
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("histories")
    suspend fun getAllHistory(
    ): HistoryResponse

    @GET("favorite")
    suspend fun getAllFavorite(
    ): HistoryResponse

    @Multipart
    @POST("predict")
    suspend fun upload(
        @Part file: MultipartBody.Part,
    ): DiseaseDetectionResponse

    @GET("history")
    suspend fun getAllHistorySkinAnalyze(
    ): List<DiseaseDetectionResponse>

    @GET("getFavorite")
    suspend fun getAllFavoriteSkinAnalyze(
    ): List<DiseaseDetectionResponse>

}