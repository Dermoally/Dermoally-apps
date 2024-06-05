package com.polije.dermoally_apps.base

import android.app.Application
import com.polije.dermoally_apps.utils.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
class BaseApps: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApps)
            modules(koinModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}