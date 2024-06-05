package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.auth.LoginRequest
import com.polije.dermoally_apps.data.auth.LoginResponse
import com.polije.dermoally_apps.data.auth.RegisterRequest
import com.polije.dermoally_apps.data.auth.RegisterResponse
import com.polije.dermoally_apps.data.model.User
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.prefs.UserPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.polije.dermoally_apps.utils.PrefsManager
import com.polije.dermoally_apps.utils.koinModules
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules

interface AuthRepository {
    fun login(user: LoginRequest): Flow<ApiStatus<LoginResponse>>
    fun register(user: RegisterRequest): Flow<ApiStatus<RegisterResponse>>
    fun logout(): Boolean
}

class AuthRepositoryImpl(private val apiServices: ApiServices, private val prefs: UserPrefs) : AuthRepository {
    override fun login(user: LoginRequest): Flow<ApiStatus<LoginResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.login(user)
            if (!response.error) {
                val user = response.loginResult
                prefs.saveSession(User(user.email, user.token, true))
                reloadKoinModules()
                emit(ApiStatus.Success(response))
            } else {
                emit(ApiStatus.Error(response.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

    override fun register(user: RegisterRequest): Flow<ApiStatus<RegisterResponse>> =
        flow {
            try {
                emit(ApiStatus.Loading)
                val response = apiServices.register(user)
                if (!response.error) {
                    emit(ApiStatus.Success(response))
                } else {
                    emit(ApiStatus.Error(response.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiStatus.Error(e.message.toString()))
            }
        }

    override fun logout(): Boolean {
        return try {
            runBlocking {
                prefs.logout()
            }
            reloadKoinModules()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun reloadKoinModules() {
        unloadKoinModules(koinModules)
        loadKoinModules(koinModules)
    }
    companion object {
        const val TAG = "AuthRepository"
    }
}