package com.polije.dermoally_apps.data.injection

import com.polije.dermoally_apps.data.repository.AuthRepository
import com.polije.dermoally_apps.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}