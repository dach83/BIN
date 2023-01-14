package com.github.dach83.bin.di

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.local.room.RoomCardDataSource
import com.github.dach83.bin.core.data.remote.RemoteCardDataSource
import com.github.dach83.bin.core.data.remote.retrofit.RetrofitCardDataSource
import com.github.dach83.bin.core.data.repository.CardRepositoryImpl
import com.github.dach83.bin.core.data.time.AndroidClock
import com.github.dach83.bin.core.data.time.Clock
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.bin.core.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val coreModule = module {

    single<Clock> { AndroidClock() }

    single<LocalCardDataSource> { RoomCardDataSource(get(), get()) }

    single<RemoteCardDataSource> { RetrofitCardDataSource(get()) }

    single<CardRepository> { CardRepositoryImpl(get(), get()) }

    viewModelOf(::MainViewModel)
}
