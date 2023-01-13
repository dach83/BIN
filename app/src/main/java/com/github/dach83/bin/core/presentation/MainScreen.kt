package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.TabItem
import com.github.dach83.bin.core.presentation.navigation.TabsContent

private val navigationTabs = listOf(
    TabItem.Search,
    TabItem.History
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(tabs: List<TabItem> = navigationTabs) {
    val pagerState = rememberPagerState()
    var uiState by remember {
        mutableStateOf(
            MainUiState(
                cardNumber = "",
                selectedTabIndex = pagerState.currentPage
            )
        )
    }

    LaunchedEffect(key1 = uiState) {
        pagerState.animateScrollToPage(uiState.selectedTabIndex)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabsBar(
            tabs = tabs,
            selectedTabIndex = uiState.selectedTabIndex,
            onTabClick = { index ->
                uiState = uiState.copy(selectedTabIndex = index)
            }
        )
        TabsContent(
            tabs = tabs,
            pagerState = pagerState,
            uiState = uiState,
            updateUiState = { newUiState ->
                uiState = newUiState
            }
        )
    }
}
