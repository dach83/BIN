package com.github.dach83.bin.di

import com.github.dach83.bin.feature.search.domain.usecase.LoadCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.LoadCardDetailsImpl
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumberImpl
import com.github.dach83.bin.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {

    single<LoadCardDetails> { LoadCardDetailsImpl(get()) }

    single<ValidateCardNumber> { ValidateCardNumberImpl() }

    viewModelOf(::SearchViewModel)
}
