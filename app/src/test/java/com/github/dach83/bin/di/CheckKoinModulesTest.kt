package com.github.dach83.bin.di

import android.content.Context
import com.github.dach83.sharedtestcode.di.testModule
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class CheckKoinModulesTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `check koin modules in MainApplication`() {
        koinApplication {
            modules(appModule)
            checkModules() {
                withInstance<Context>()
            }
        }
    }

    @Test
    fun `check koin modules in TestApplication`() {
        koinApplication {
            modules(appModule, testModule)
            checkModules() {
                withInstance<Context>()
            }
        }
    }
}
