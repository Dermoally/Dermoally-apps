package com.polije.dermoally_apps.utils

import com.polije.dermoally_apps.BuildConfig
import com.polije.dermoally_apps.data.injection.networkModule
import com.polije.dermoally_apps.data.injection.repositoryModule
import com.polije.dermoally_apps.data.injection.viewModelModule


val koinModules = listOf(
    networkModule,
    repositoryModule,
    viewModelModule,
)

const val PREFS_NAME: String = "auth_pref"
const val TOKEN_KEY: String = "auth_token"
const val NAME_KEY: String = "auth_name"

const val API_URL = BuildConfig.API_URL