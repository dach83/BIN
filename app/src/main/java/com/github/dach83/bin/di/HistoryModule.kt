package com.github.dach83.bin.di

import com.github.dach83.bin.feature.history.domain.usecase.CardsSearchHistory
import com.github.dach83.bin.feature.history.domain.usecase.CardsSearchHistoryImpl
import com.github.dach83.bin.feature.history.presentation.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val historyModule = module {

    single<CardsSearchHistory> { CardsSearchHistoryImpl(get()) }

    viewModelOf(::HistoryViewModel)
}
