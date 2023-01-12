package com.github.dach83.bin

import android.app.Application
import com.github.dach83.bin.di.remoteModule
import com.github.dach83.bin.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                searchModule,
                remoteModule
            )
        }
    }
}
