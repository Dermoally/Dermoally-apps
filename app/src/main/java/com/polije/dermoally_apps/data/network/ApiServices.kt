package com.polije.dermoally_apps.data.network

import com.polije.dermoally_apps.data.model.auth.LoginRequest
import com.polije.dermoally_apps.data.model.auth.LoginResponse
import com.polije.dermoally_apps.data.model.auth.RegisterRequest
import com.polije.dermoally_apps.data.model.auth.RegisterResponse
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.model.disease.FavoriteRequest
import com.polije.dermoally_apps.data.model.disease.FavoriteResponse
import com.polije.dermoally_apps.data.model.disease.MedicationResponses
import com.polije.dermoally_apps.data.model.disease.SkinAnalyzeResponse
import com.polije.dermoally_apps.data.model.user.UpdateUserResponse
import com.polije.dermoally_apps.data.model.user.UserRequest
import com.polije.dermoally_apps.data.model.user.UserResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("user")
    suspend fun getUserInfo(
    ): UserResponse

    @PUT("user")
    suspend fun updateUserInfo(
        @Body userRequest: UserRequest
    ): UpdateUserResponse

    @Multipart
    @POST("predict")
    suspend fun upload(
        @Part file: MultipartBody.Part,
    ): SkinAnalyzeResponse

    @GET("history")
    suspend fun getAllHistorySkinAnalyze(
    ): List<DiseaseDetectionResponse>

    @GET("getFavorite")
    suspend fun getAllFavoriteSkinAnalyze(
    ): List<DiseaseDetectionResponse>

    @GET("predict/recent")
    suspend fun getAllRecentSkinAnalyze(
    ): List<DiseaseDetectionResponse>

    @GET("medications")
    suspend fun getAllMedications(
    ): List<MedicationResponses>

    @POST("updateFavorite")
    suspend fun updateStatusFavorite(
        @Body favoriteRequest: FavoriteRequest
    ): FavoriteResponse
}