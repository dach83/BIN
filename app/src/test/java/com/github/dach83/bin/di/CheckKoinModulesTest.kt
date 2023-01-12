package com.github.dach83.bin.di

import com.github.dach83.sharedtestcode.testModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class CheckKoinModulesTest : KoinTest {

    @Test
    fun `check search module`() {
        searchModule.verify()
    }

    @Test
    fun `check remote module`() {
        remoteModule.verify()
    }

    @Test
    fun `check test module`() {
        testModule.verify()
    }
}
