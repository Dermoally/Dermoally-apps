package com.polije.dermoally_apps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polije.dermoally_apps.data.prefs.UserPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel(val prefs: UserPrefs): ViewModel() {
    val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun checkLoginStatus() {
        viewModelScope.launch {
            val user = prefs.getSession().first()
            _isLogin.value = user.isLogin
        }
    }

    fun logout() {
        viewModelScope.launch {
            prefs.logout()
            _isLogin.value = false
        }
    }

    init {
        checkLoginStatus()
    }

}