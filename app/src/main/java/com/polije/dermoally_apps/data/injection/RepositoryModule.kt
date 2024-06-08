package com.polije.dermoally_apps.data.injection

import com.polije.dermoally_apps.data.repository.AuthRepository
import com.polije.dermoally_apps.data.repository.AuthRepositoryImpl
import com.polije.dermoally_apps.data.repository.FavoriteRepository
import com.polije.dermoally_apps.data.repository.FavoriteRepositoryImpl
import com.polije.dermoally_apps.data.repository.HistoryRepository
import com.polije.dermoally_apps.data.repository.HistoryRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}