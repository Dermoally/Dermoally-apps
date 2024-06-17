package com.polije.dermoally_apps.data.injection

import android.content.Context
import com.polije.dermoally_apps.data.network.ApiServices
import com.polije.dermoally_apps.data.prefs.UserPrefs
import com.polije.dermoally_apps.utils.API_URL
import com.polije.dermoally_apps.utils.InterceptorHeader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single { UserPrefs.getInstance(get()) }
    single {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor(get(), androidContext()))
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }
    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(ApiServices::class.java)
    }
}

private fun headerInterceptor(userPrefs: UserPrefs, context: Context): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    val endpointsRequiringAuth = listOf("history", "predict", "getFavorite", "user", "predict/recent", "medications", "updateFavorite")

    return InterceptorHeader(headers, userPrefs, endpointsRequiringAuth, context)
}
