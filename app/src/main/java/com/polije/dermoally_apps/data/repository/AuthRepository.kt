package com.polije.dermoally_apps.data.repository

import android.util.Log
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
import com.polije.dermoally_apps.utils.koinModules
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules

interface AuthRepository {
    fun login(req: LoginRequest): Flow<ApiStatus<LoginResponse>>
    fun register(req: RegisterRequest): Flow<ApiStatus<RegisterResponse>>
    fun logout(): Boolean
}

class AuthRepositoryImpl(private val apiServices: ApiServices, private val prefs: UserPrefs) : AuthRepository {
    override fun login(req: LoginRequest): Flow<ApiStatus<LoginResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.login(req)
            if (!response.error) {
                response.token?.let {
                    prefs.saveSession(User(it, true))
                    reloadKoinModules()
                }
                emit(ApiStatus.Success(response))
            } else {
                emit(ApiStatus.Error(response.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

    override fun register(req: RegisterRequest): Flow<ApiStatus<RegisterResponse>> =
        flow {
            try {
                emit(ApiStatus.Loading)
                val response = apiServices.register(req)
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