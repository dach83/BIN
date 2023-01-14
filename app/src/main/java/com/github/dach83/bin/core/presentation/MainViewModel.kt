package com.github.dach83.bin.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var uiState by mutableStateOf(MainUiState.INITIAL)
        private set

    fun displayTab(tabIndex: Int) {
        uiState = uiState.copy(selectedTabIndex = tabIndex)
    }

    fun updateState(state: MainUiState) {
        uiState = state
    }
}
