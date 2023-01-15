package com.github.dach83.bin.core.presentation

import com.github.dach83.bin.core.presentation.navigation.NavigationScreen

data class MainUiState(
    val selectedTabIndex: Int,
    val cardNumber: String
) {
    companion object {
        val INITIAL = MainUiState(
            selectedTabIndex = NavigationScreen.SEARCH.tabIndex,
            cardNumber = ""
        )
    }
}
