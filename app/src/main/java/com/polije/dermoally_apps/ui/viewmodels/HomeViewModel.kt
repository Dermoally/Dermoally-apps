package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepository
import com.polije.dermoally_apps.data.repository.UserRepository
import com.polije.dermoally_apps.data.user.UserResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository, private val repository: SkinAnalyzeRepository): ViewModel() {
    private val _userResult = MutableLiveData<ApiStatus<UserResponse>>()
    val userResult: LiveData<ApiStatus<UserResponse>> = _userResult

    private val _recentResult = MutableLiveData<ApiStatus<List<DiseaseDetectionResponse>>>()
    val recentResult: LiveData<ApiStatus<List<DiseaseDetectionResponse>>> = _recentResult

    fun getRecentResult() {
        viewModelScope.launch {
            repository.getAllSkinAnalyze().collect {
                _recentResult.value = it
            }
        }
    }
    fun getUserInfo(){
        viewModelScope.launch {
            userRepository.getUserInfo().collect {
                _userResult.value = it
            }
        }
    }
}