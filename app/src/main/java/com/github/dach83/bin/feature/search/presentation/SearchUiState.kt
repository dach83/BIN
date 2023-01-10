package com.github.dach83.bin.feature.search.presentation

import androidx.annotation.StringRes
import com.github.dach83.bin.feature.search.domain.model.CardDetails

data class SearchUiState(
    val cardNumber: String = "",
    val cardDetails: CardDetails = CardDetails(),
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null
)
