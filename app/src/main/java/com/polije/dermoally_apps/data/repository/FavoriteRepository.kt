package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FavoriteRepository {
    fun getAllFavorite(): Flow<ApiStatus<List<DiseaseDetectionResponse>>>
}

class FavoriteRepositoryImpl(private val apiServices: ApiServices) : FavoriteRepository {
    override fun getAllFavorite(): Flow<ApiStatus<List<DiseaseDetectionResponse>>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllFavoriteSkinAnalyze()
            emit(ApiStatus.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

}