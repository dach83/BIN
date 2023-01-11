package com.github.dach83.bin.di

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class CheckKoinModulesTest : KoinTest {

    @Test
    fun `check navigation module`() {
        navigationModule.verify()
    }

    @Test
    fun `check search module`() {
        searchModule.verify()
    }
}
