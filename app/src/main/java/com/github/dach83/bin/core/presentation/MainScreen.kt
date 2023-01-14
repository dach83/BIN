package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.NavigationBar
import com.github.dach83.bin.core.presentation.navigation.NavigationPager
import com.github.dach83.bin.core.presentation.navigation.NavigationState
import com.github.dach83.bin.core.presentation.navigation.NavigationTab

private val navigationTabs = listOf(
    NavigationTab.Search,
    NavigationTab.History
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(tabs: List<NavigationTab> = navigationTabs) {
    val pagerState = rememberPagerState()
    var navigationState by remember {
        mutableStateOf(
            NavigationState(
                cardNumber = "",
                selectedTabIndex = pagerState.currentPage
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
