package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.history.HistoryResponse
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface HistoryRepository {
    fun getAllHistory(): Flow<ApiStatus<HistoryResponse>>
}

class HistoryRepositoryImpl(private val apiServices: ApiServices) : HistoryRepository {
    override fun getAllHistory(): Flow<ApiStatus<HistoryResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllHistory()
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