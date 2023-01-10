package com.github.dach83.bin.feature.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var uiState by mutableStateOf(SearchUiState.INITIAL)
        private set
}
