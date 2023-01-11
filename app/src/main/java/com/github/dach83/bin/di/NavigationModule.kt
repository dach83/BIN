package com.github.dach83.bin.di

import com.github.dach83.bin.core.presentation.navigation.NAVIGATION_TABS
import com.github.dach83.bin.core.presentation.navigation.TabItem
import org.koin.core.qualifier.named
import org.koin.dsl.module

val navigationModule = module {
    single(named(NAVIGATION_TABS)) {
        listOf(
            TabItem.Search,
            TabItem.History
        )
    }
}
