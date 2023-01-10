package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.dach83.bin.core.presentation.navigation.TabItem
import com.github.dach83.bin.core.presentation.navigation.Tabs
import com.github.dach83.bin.core.presentation.navigation.TabsContent

val navigationTabs = listOf(
    TabItem.Search,
    TabItem.History
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(tabs: List<TabItem> = navigationTabs) {
    val pagerState = rememberPagerState()
    Scaffold() {
        Column(modifier = Modifier.padding(it)) {
            Tabs(tabs, pagerState)
            TabsContent(tabs, pagerState)
        }
    }
}
