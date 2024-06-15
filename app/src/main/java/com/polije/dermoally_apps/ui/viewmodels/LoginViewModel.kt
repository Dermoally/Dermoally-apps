package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.auth.LoginRequest
import com.polije.dermoally_apps.data.auth.LoginResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(val repository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<ApiStatus<LoginResponse>>()
    val loginResult: LiveData<ApiStatus<LoginResponse>> = _loginResult

    fun login(req: LoginRequest) {
        viewModelScope.launch {
            repository.login(req).collect {
                _loginResult.value = it
            }
        }
    }
}