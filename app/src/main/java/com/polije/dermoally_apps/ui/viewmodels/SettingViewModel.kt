package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.user.UserResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.prefs.UserPrefs
import com.polije.dermoally_apps.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingViewModel(val prefs: UserPrefs, private val userRepository: UserRepository): ViewModel() {
    val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _userResult = MutableLiveData<ApiStatus<UserResponse>>()
    val userResult: LiveData<ApiStatus<UserResponse>> = _userResult

    fun logout() {
        viewModelScope.launch {
            prefs.logout()
            _isLogin.value = false
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