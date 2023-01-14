package com.github.dach83.bin

import android.app.Application
import com.github.dach83.bin.di.appModule
import com.github.dach83.sharedtestcode.di.testModule
import org.koin.core.context.GlobalContext.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, testModule)
        }
    }
}
