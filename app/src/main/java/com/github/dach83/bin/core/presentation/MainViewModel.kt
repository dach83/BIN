package com.github.dach83.bin.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.dach83.bin.core.presentation.navigation.NavigationScreen

@OptIn(ExperimentalFoundationApi::class)
class MainViewModel : ViewModel() {

    var uiState by mutableStateOf(MainUiState.INITIAL)
        private set

    fun changeSelectedTab(tabIndex: Int) {
        uiState = uiState.copy(selectedTabIndex = tabIndex)
    }

    fun showCardDetails(cardNumber: String) {
        uiState = uiState.copy(
            selectedTabIndex = NavigationScreen.SEARCH.tabIndex,
            cardNumber = cardNumber
        )
    }
}
