package com.github.dach83.sharedtestcode

import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.fake.FakeCardRepository
import org.koin.dsl.module

val testModule = module {
    single<CardRepository> { FakeCardRepository() }
}
