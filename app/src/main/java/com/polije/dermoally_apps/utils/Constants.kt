package com.polije.dermoally_apps.utils

import android.content.Context
import android.widget.Toast
import com.polije.dermoally_apps.BuildConfig
import com.polije.dermoally_apps.data.injection.dataStoreModule
import com.polije.dermoally_apps.data.injection.networkModule
import com.polije.dermoally_apps.data.injection.repositoryModule
import com.polije.dermoally_apps.data.injection.viewModelModule


val koinModules = listOf(
    dataStoreModule,
    networkModule,
    repositoryModule,
    viewModelModule,
)

const val PREFS_NAME: String = "auth_pref"
const val TOKEN_KEY: String = "auth_token"
const val NAME_KEY: String = "auth_name"

const val API_URL = "http://192.168.46.87:9000/"

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}