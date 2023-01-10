package com.github.dach83.bin.feature.search.presentation

import androidx.annotation.StringRes

data class SearchUiState(
    val cardNumber: String = "",
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null
) {
    companion object {
        val INITIAL = SearchUiState(
            cardNumber = "",
            isLoading = false,
            error = null
        )
    }
}
