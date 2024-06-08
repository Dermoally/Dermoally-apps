package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.disease.HistoryResponse
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FavoriteRepository {
    fun getAllFavorite(): Flow<ApiStatus<HistoryResponse>>
}

class FavoriteRepositoryImpl(private val apiServices: ApiServices) : FavoriteRepository {
    override fun getAllFavorite(): Flow<ApiStatus<HistoryResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllFavorite()
            if (!response.error) {
                emit(ApiStatus.Success(response))
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }
}