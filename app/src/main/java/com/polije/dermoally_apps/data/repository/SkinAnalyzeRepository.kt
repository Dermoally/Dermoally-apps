package com.polije.dermoally_apps.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.model.disease.FavoriteRequest
import com.polije.dermoally_apps.data.model.disease.FavoriteResponse
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.utils.extensions.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SkinAnalyzeRepository  {
    fun getAllSkinAnalyze(): Flow<ApiStatus<List<DiseaseDetectionResponse>>>
    fun getAllRecentSkinAnalyze(): Flow<ApiStatus<List<DiseaseDetectionResponse>>>
    fun uploadSkinAnalyze(
        fileUri: Uri,
    ): Flow<ApiStatus<DiseaseDetectionResponse>>

    fun updateFavorite(favoriteRequest: FavoriteRequest): Flow<ApiStatus<FavoriteResponse>>
}

class SkinAnalyzeRepositoryImpl(private val apiServices: ApiServices): SkinAnalyzeRepository{
    override fun getAllSkinAnalyze(): Flow<ApiStatus<List<DiseaseDetectionResponse>>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllHistorySkinAnalyze()
            emit(ApiStatus.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

    override fun getAllRecentSkinAnalyze(): Flow<ApiStatus<List<DiseaseDetectionResponse>>>  = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllRecentSkinAnalyze()
            emit(ApiStatus.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }


    override fun uploadSkinAnalyze(fileUri: Uri): Flow<ApiStatus<DiseaseDetectionResponse>> = flow {
        try {
        emit(ApiStatus.Loading)
        val file = fileUri.toFile().reduceFileImage()
        val requestImageFile = file.asRequestBody("image/*".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "file", file.name, requestImageFile
        )
        val response = apiServices.upload(multipartBody)
            if (!response.error) {
                emit(ApiStatus.Success(response.data))
            } else {
                emit(ApiStatus.Error("failed to analyze skin, skin disease not found"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

    override fun updateFavorite(favoriteRequest: FavoriteRequest): Flow<ApiStatus<FavoriteResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.updateStatusFavorite(favoriteRequest)
            if (!response.error) {
                emit(ApiStatus.Success(response))
            } else {
                emit(ApiStatus.Error(response.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }
}