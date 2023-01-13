package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.TabItem
import com.github.dach83.bin.core.presentation.navigation.TabsBar
import com.github.dach83.bin.feature.history.presentation.HistoryScreen
import com.github.dach83.bin.feature.search.presentation.SearchScreen
import kotlinx.coroutines.launch

private val navigationTabs = listOf(
    TabItem.Search,
    TabItem.History
)

typealias ComposableFun = @Composable () -> Unit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(tabs: List<TabItem> = navigationTabs) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    var cardNumber by remember { mutableStateOf("") }

    var screens = listOf(
        SearchScreen(cardNumber = cardNumber),
        HistoryScreen(onItemClick = {})
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TabsBar(
            tabs = tabs,
            selectedTabIndex = pagerState.currentPage,
            onTabClick = { page ->
                scope.launch {
                    pagerState.scrollToPage(page)
                }
            }
        )
    }

    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState
    ) { page ->
        screens[page]
    }
}
