package com.polije.dermoally_apps.data.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.polije.dermoally_apps.data.prefs.UserPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

val dataStoreModule = module {
    single { androidContext().dataStore }
    single { UserPrefs.getInstance(get()) }
}