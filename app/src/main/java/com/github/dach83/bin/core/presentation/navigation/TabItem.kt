package com.github.dach83.bin.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.dach83.bin.R

sealed class TabItem(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object Search : TabItem(
        title = R.string.search_tab,
        icon = Icons.Default.Search
    )

    object History : TabItem(
        title = R.string.history_tab,
        icon = Icons.Default.List
    )
}
