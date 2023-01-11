package com.github.dach83.bin.di

import com.github.dach83.bin.feature.search.data.repository.SearchRepositoryImpl
import com.github.dach83.bin.feature.search.domain.repository.SearchRepository
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetailsImpl
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumberImpl
import com.github.dach83.bin.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)

    single<SearchRepository> { SearchRepositoryImpl(get()) }

    single<RequestCardDetails> { RequestCardDetailsImpl(get()) }

    single<ValidateCardNumber> { ValidateCardNumberImpl() }
}
