package com.github.dach83.bin.di

import com.github.dach83.bin.feature.history.domain.usecase.LoadSearchHistory
import com.github.dach83.bin.feature.history.presentation.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val historyModule = module {

    single<LoadSearchHistory> { LoadSearchHistory(get()) }

    viewModelOf(::HistoryViewModel)
}
