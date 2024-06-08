package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.disease.HistoryResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    private var _historyResult = MutableLiveData<ApiStatus<HistoryResponse>>()
    val historyResult: LiveData<ApiStatus<HistoryResponse>> = _historyResult

    fun getAllHistory() {
        viewModelScope.launch {
            repository.getAllHistory().collect {
                _historyResult.value = it
            }
        }
    }
}