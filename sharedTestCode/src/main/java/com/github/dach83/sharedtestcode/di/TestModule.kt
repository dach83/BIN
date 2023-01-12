package com.github.dach83.sharedtestcode

import com.github.dach83.bin.feature.search.domain.repository.SearchRepository
import com.github.dach83.sharedtestcode.fake.FakeSearchRepository
import org.koin.dsl.module

val testModule = module {
    single<SearchRepository> { FakeSearchRepository() }
}
