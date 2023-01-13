package com.github.dach83.bin.core.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.github.dach83.bin.core.presentation.MainUiState

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    uiState: MainUiState,
    updateUiState: (MainUiState) -> Unit
) {
    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState
    ) { page ->
        tabs[page].screen(
            cardNumber = uiState.cardNumber,
            showCardDetails = { cardNumber ->
                val newUiState = uiState.copy(
                    cardNumber = cardNumber,
                    selectedTabIndex = tabs.indexOf(TabItem.Search)
                )
                updateUiState(newUiState)
            }
        )
    }
}
