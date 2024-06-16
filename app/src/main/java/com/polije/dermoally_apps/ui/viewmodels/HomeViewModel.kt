package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.UserRepository
import com.polije.dermoally_apps.data.user.UserResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _userResult = MutableLiveData<ApiStatus<UserResponse>>()
    val userResult: LiveData<ApiStatus<UserResponse>> = _userResult

    fun getUserInfo(){
        viewModelScope.launch {
            userRepository.getUserInfo().collect {
                _userResult.value = it
            }
        }
    }
}