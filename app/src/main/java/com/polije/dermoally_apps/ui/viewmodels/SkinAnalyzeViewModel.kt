package com.polije.dermoally_apps.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.model.disease.SkinAnalyzeResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepository
import kotlinx.coroutines.launch

class SkinAnalyzeViewModel(private val repository: SkinAnalyzeRepository): ViewModel() {
    private val _uploadSkinAnalyzeResult =
        MutableLiveData<ApiStatus<SkinAnalyzeResponse>>()
    val uploadSkinAnalyzeResult: LiveData<ApiStatus<SkinAnalyzeResponse>> =
        _uploadSkinAnalyzeResult

    fun uploadSkinAnalyze(
        fileUri: Uri,
    ) {
        viewModelScope.launch {
            repository.uploadSkinAnalyze(fileUri).collect {
                _uploadSkinAnalyzeResult.value = it
            }
        }
    }
}