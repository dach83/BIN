package com.github.dach83.bin.di

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.local.room.LocalCardDataSourceImpl
import com.github.dach83.bin.core.data.remote.RemoteCardDataSource
import com.github.dach83.bin.core.data.remote.retrofit.RemoteCardDataSourceImpl
import com.github.dach83.bin.core.data.repository.CardRepositoryImpl
import com.github.dach83.bin.core.domain.repository.CardRepository
import org.koin.dsl.module

val coreModule = module {

    single<LocalCardDataSource> { LocalCardDataSourceImpl() }

    single<RemoteCardDataSource> { RemoteCardDataSourceImpl(get()) }

    single<CardRepository> { CardRepositoryImpl(get(), get()) }
}
