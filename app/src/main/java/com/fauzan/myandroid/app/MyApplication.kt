package com.fauzan.myandroid.app

import android.app.Application
import com.fauzan.myandroid.BuildConfig
import com.fauzan.myandroid.app.di.*
import com.fauzan.myandroid.app.timber.ReleaseTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Set timber
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree()) else Timber.plant(ReleaseTree())

        // Adding Koin modules
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            // modules
             modules(listOf(viewModelModule, apiModule, netModule, repositoryModule, databaseModule))
        }
    }
}