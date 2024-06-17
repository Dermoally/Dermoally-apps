package com.polije.dermoally_apps.data.injection

import com.polije.dermoally_apps.data.repository.AuthRepository
import com.polije.dermoally_apps.data.repository.AuthRepositoryImpl
import com.polije.dermoally_apps.data.repository.FavoriteRepository
import com.polije.dermoally_apps.data.repository.FavoriteRepositoryImpl
import com.polije.dermoally_apps.data.repository.MedicationRepository
import com.polije.dermoally_apps.data.repository.MedicationRepositoryImpl
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepository
import com.polije.dermoally_apps.data.repository.SkinAnalyzeRepositoryImpl
import com.polije.dermoally_apps.data.repository.UserRepository
import com.polije.dermoally_apps.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    single<SkinAnalyzeRepository> { SkinAnalyzeRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MedicationRepository> { MedicationRepositoryImpl(get()) }
}