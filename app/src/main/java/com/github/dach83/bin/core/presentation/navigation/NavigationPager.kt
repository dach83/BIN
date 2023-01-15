package com.github.dach83.bin.core.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun NavigationPager(
    tabs: List<NavigationTab>,
    pagerState: PagerState,
    cardNumber: String,
    showCardDetails: (cardNumber: String) -> Unit
) {
    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState
    ) { page ->
        tabs[page].screen(cardNumber, showCardDetails)
    }
}
