package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.*
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(tabs: List<NavigationTab> = get()) {
    val pagerState = rememberPagerState()
    var navigationState by remember {
        mutableStateOf(
            NavigationState(
                cardNumber = "",
                selectedTabIndex = NavigationScreen.SEARCH.tabIndex
            )
        )
    }

    LaunchedEffect(key1 = navigationState) {
        pagerState.animateScrollToPage(navigationState.selectedTabIndex)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NavigationBar(
            tabs = tabs,
            selectedTabIndex = navigationState.selectedTabIndex,
            onTabClick = { index ->
                navigationState = navigationState.copy(selectedTabIndex = index)
            }
        )
        NavigationPager(
            tabs = tabs,
            pagerState = pagerState,
            navigationState = navigationState,
            updateNavigationState = { state ->
                navigationState = state
            }
        )
    }
}
