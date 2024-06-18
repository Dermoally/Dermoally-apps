package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.auth.LoginRequest
import com.polije.dermoally_apps.data.model.auth.LoginResponse
import com.polije.dermoally_apps.data.model.disease.FavoriteRequest
import com.polije.dermoally_apps.data.model.disease.FavoriteResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepository
import kotlinx.coroutines.launch

class ResultViewModel( private val repository: SkinAnalyzeRepository): ViewModel() {
    private val _updateFavoriteResult = MutableLiveData<ApiStatus<FavoriteResponse>>()
    val updateFavoriteResult: LiveData<ApiStatus<FavoriteResponse>> = _updateFavoriteResult

    fun updateFavorite(req: FavoriteRequest) {
        viewModelScope.launch {
            repository.updateFavorite(req).collect {
                _updateFavoriteResult.value = it
            }
        }
    }
}