package com.github.dach83.bin.di

import com.github.dach83.bin.feature.search.domain.repository.SearchRepository
import com.github.dach83.sharedtestcode.FakeSearchRepository
import org.koin.dsl.module

val testModule = module {
    single<SearchRepository> { FakeSearchRepository() }
}
