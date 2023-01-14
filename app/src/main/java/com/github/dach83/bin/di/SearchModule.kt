package com.github.dach83.bin.di

import com.github.dach83.bin.feature.search.domain.usecase.*
import com.github.dach83.bin.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {

    single<ValidateCardNumber> { ValidateCardNumber() }

    single<LoadCardDetails> { LoadCardDetails(get()) }

    single<UpdateSearchHistory> { UpdateSearchHistory(get()) }

    viewModelOf(::SearchViewModel)
}
