package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.disease.HistoryResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {
    private val _favoriteResult = MutableLiveData<ApiStatus<List<DiseaseDetectionResponse>>>()
    val favoriteResult: LiveData<ApiStatus<List<DiseaseDetectionResponse>>> = _favoriteResult


    fun getAllFavorite() {
        viewModelScope.launch {
            repository.getAllFavorite().collect {
                _favoriteResult.value = it
            }
        }
    }
}