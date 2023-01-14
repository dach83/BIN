package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    val pagerState = rememberPagerState()
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = uiState) {
        pagerState.animateScrollToPage(uiState.selectedTabIndex)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NavigationBar(
            tabs = tabs,
            selectedTabIndex = uiState.selectedTabIndex,
            onTabClick = viewModel::displayTab
        )
        NavigationPager(
            tabs = tabs,
            pagerState = pagerState,
            mainUiState = uiState,
            updateUiState = viewModel::updateState
        )
    }
}
