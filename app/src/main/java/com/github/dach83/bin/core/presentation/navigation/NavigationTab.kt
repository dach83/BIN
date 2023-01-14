package com.github.dach83.bin.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

private typealias Screen = @Composable (
    cardNumber: String,
    showCardDetails: (cardNumber: String) -> Unit
) -> Unit

class NavigationTab(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: Screen
)
