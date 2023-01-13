package com.github.dach83.bin.di

import org.koin.dsl.module

val appModule = module {
    includes(
        roomModule,
        retrofitModule,
        coreModule,
        searchModule,
        historyModule
    )
}
