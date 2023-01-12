package com.github.dach83.bin.di

import com.github.dach83.sharedtestcode.testModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckKoinModulesTest : KoinTest {

    @Test
    fun `check koin modules in MainApplication`() {
        koinApplication {
            modules(appModule)
            checkModules()
        }
    }

    @Test
    fun `check koin modules in TestApplication`() {
        koinApplication {
            modules(appModule, testModule)
            checkModules()
        }
    }
}
