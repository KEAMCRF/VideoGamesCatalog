package com.cinepolis.videogamesapp

import android.app.Application
import com.cinepolis.videogamesapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideogamesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VideogamesApp)
            modules(appModule)
        }
    }
}