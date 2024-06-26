package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.auth.RegisterRequest
import com.polije.dermoally_apps.data.model.auth.RegisterResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _registerResult = MutableLiveData<ApiStatus<RegisterResponse>>()
    val registerResult: LiveData<ApiStatus<RegisterResponse>> = _registerResult

    fun register(req: RegisterRequest) {
        viewModelScope.launch {
            repository.register(req).collect {
                _registerResult.value = it
            }
        }
    }
}