package com.github.dach83.bin.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.history.presentation.HistoryScreen
import com.github.dach83.bin.feature.search.presentation.SearchScreen

sealed class TabItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
) {
    object Search : TabItem(
        title = R.string.search_tab,
        icon = Icons.Default.Search,
        screen = { SearchScreen() }
    )

    object History : TabItem(
        title = R.string.history_tab,
        icon = Icons.Default.List,
        screen = { HistoryScreen() }
    )
}
