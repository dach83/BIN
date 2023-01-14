package com.github.dach83.bin.core.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.github.dach83.bin.core.presentation.MainUiState

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun NavigationPager(
    tabs: List<NavigationTab>,
    pagerState: PagerState,
    mainUiState: MainUiState,
    updateUiState: (MainUiState) -> Unit
) {
    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState
    ) { page ->
        tabs[page].screen(
            cardNumber = mainUiState.cardNumber,
            showCardDetails = { cardNumber ->
                val newState = mainUiState.copy(
                    cardNumber = cardNumber,
                    selectedTabIndex = NavigationScreen.SEARCH.tabIndex
                )
                updateUiState(newState)
            }
        )
    }
}
