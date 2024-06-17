package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.model.user.UpdateUserResponse
import com.polije.dermoally_apps.data.model.user.UserRequest
import com.polije.dermoally_apps.data.model.user.UserResponse
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: UserRepository) : ViewModel() {
    private val _updateUserResult = MutableLiveData<ApiStatus<UpdateUserResponse>>()
    val updateUserResult: LiveData<ApiStatus<UpdateUserResponse>> = _updateUserResult

    private val _userResult = MutableLiveData<ApiStatus<UserResponse>>()
    val userResult: LiveData<ApiStatus<UserResponse>> = _userResult

    fun getUserInfo(){
        viewModelScope.launch {
            repository.getUserInfo().collect {
                _userResult.value = it
            }
        }
    }
    fun updateUser(req: UserRequest) {
        viewModelScope.launch {
            repository.updateUserInfo(req).collect {
                _updateUserResult.value = it
            }
        }
    }
}