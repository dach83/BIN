package com.github.dach83.bin.core.presentation

import com.github.dach83.bin.core.presentation.navigation.NavigationScreen

data class MainUiState(
    val cardNumber: String,
    val selectedTabIndex: Int
) {
    companion object {
        val INITIAL = MainUiState(
            cardNumber = "",
            selectedTabIndex = NavigationScreen.SEARCH.tabIndex
        )
    }
}
