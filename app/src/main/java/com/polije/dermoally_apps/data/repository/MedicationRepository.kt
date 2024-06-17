package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.model.disease.MedicationResponses
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MedicationRepository {
    fun getAllMedications(): Flow<ApiStatus<List<MedicationResponses>>>
}

class MedicationRepositoryImpl(private val apiServices: ApiServices): MedicationRepository{
    override fun getAllMedications(): Flow<ApiStatus<List<MedicationResponses>>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.getAllMedications()
            emit(ApiStatus.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }
}