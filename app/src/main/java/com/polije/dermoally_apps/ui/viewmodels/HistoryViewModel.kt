package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.disease.HistoryResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.HistoryRepository
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: SkinAnalyzeRepository) : ViewModel() {
    private val _historyResult = MutableLiveData<ApiStatus<List<DiseaseDetectionResponse>>>()
    val historyResult: LiveData<ApiStatus<List<DiseaseDetectionResponse>>> = _historyResult

    fun getAllHistory() {
        viewModelScope.launch {
            repository.getAllSkinAnalyze().collect {
                _historyResult.value = it
            }
        }
    }
}