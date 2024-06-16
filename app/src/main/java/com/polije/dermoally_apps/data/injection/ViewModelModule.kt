package com.polije.dermoally_apps.data.injection

import com.polije.dermoally_apps.ui.viewmodels.AuthViewModel
import com.polije.dermoally_apps.ui.viewmodels.FavoriteViewModel
import com.polije.dermoally_apps.ui.viewmodels.HistoryViewModel
import com.polije.dermoally_apps.ui.viewmodels.HomeViewModel
import com.polije.dermoally_apps.ui.viewmodels.LoginViewModel
import com.polije.dermoally_apps.ui.viewmodels.RegisterViewModel
import com.polije.dermoally_apps.ui.viewmodels.SkinAnalyzeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { SkinAnalyzeViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}