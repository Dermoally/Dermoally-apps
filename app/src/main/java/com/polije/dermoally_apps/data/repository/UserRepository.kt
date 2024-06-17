package com.polije.dermoally_apps.data.repository

import com.polije.dermoally_apps.data.model.user.UpdateUserResponse
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.data.model.user.UserRequest
import com.polije.dermoally_apps.data.model.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {

    fun getUserInfo(): Flow<ApiStatus<UserResponse>>
    fun updateUserInfo(request: UserRequest): Flow<ApiStatus<UpdateUserResponse>>
}

class UserRepositoryImpl(private val apiServices: ApiServices): UserRepository {
    override fun getUserInfo(): Flow<ApiStatus<UserResponse>> = flow {
       try {
           emit(ApiStatus.Loading)
           val response = apiServices.getUserInfo()
           emit(ApiStatus.Success(response))
       } catch (e: Exception) {
           e.printStackTrace()
           emit(ApiStatus.Error(e.message.toString()))
       }
    }

    override fun updateUserInfo(request: UserRequest): Flow<ApiStatus<UpdateUserResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = apiServices.updateUserInfo(request)
            if (!response.error){
            emit(ApiStatus.Success(response))
            } else {
                emit(ApiStatus.Error(response.message.toString()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

}
