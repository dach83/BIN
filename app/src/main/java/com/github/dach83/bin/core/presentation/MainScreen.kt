package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.*
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    tabs: List<NavigationTab> = get(),
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState
    val pagerState = rememberPagerState(initialPage = uiState.selectedTabIndex)

    SyncPagerAndViewModelState(pagerState, viewModel)

    Column(modifier = Modifier.fillMaxSize()) {
        NavigationBar(
            tabs = tabs,
            selectedTabIndex = uiState.selectedTabIndex,
            onTabClick = viewModel::changeSelectedTab
        )
        NavigationPager(
            tabs = tabs,
            pagerState = pagerState,
            cardNumber = uiState.cardNumber,
            showCardDetails = viewModel::showCardDetails
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SyncPagerAndViewModelState(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    // Update pager state if view model state changed, for example
    // after click on tab in navigation bar or after click on card number in History tab
    LaunchedEffect(key1 = viewModel.uiState.selectedTabIndex) {
        pagerState.animateScrollToPage(viewModel.uiState.selectedTabIndex)
    }

    // Update view model state if pager state changed, for example after pager swipe
    LaunchedEffect(key1 = pagerState.currentPage) {
        viewModel.changeSelectedTab(pagerState.currentPage)
    }
}
