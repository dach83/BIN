package com.github.dach83.bin.di

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import com.github.dach83.bin.R
import com.github.dach83.bin.core.presentation.navigation.NavigationScreen
import com.github.dach83.bin.core.presentation.navigation.NavigationTab
import com.github.dach83.bin.feature.history.presentation.HistoryScreen
import com.github.dach83.bin.feature.search.presentation.SearchScreen
import org.koin.core.qualifier.named
import org.koin.dsl.module

val navigationModule = module {

    single<NavigationTab>(named(NavigationScreen.SEARCH)) {
        NavigationTab(
            title = R.string.search_tab,
            icon = Icons.Default.Search,
            screen = { cardNumber, _ ->
                SearchScreen(cardNumber)
            }
        )
    }

    single<NavigationTab>(named(NavigationScreen.HISTORY)) {
        NavigationTab(
            title = R.string.history_tab,
            icon = Icons.Default.List,
            screen = { _, showCardDetails ->
                HistoryScreen(showCardDetails)
            }
        )
    }

    single<List<NavigationTab>> {
        NavigationScreen.values().map { tag ->
            get<NavigationTab>(named(tag))
        }.toList()
    }
}
