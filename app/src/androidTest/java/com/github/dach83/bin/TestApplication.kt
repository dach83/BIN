package com.github.dach83.bin

import android.app.Application
import com.github.dach83.bin.di.navigationModule
import com.github.dach83.bin.di.searchModule
import com.github.dach83.bin.di.testModule
import org.koin.core.context.GlobalContext.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                navigationModule,
                searchModule,
                testModule
            )
        }
    }
}
