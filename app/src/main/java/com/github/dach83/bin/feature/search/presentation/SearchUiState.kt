package com.github.dach83.bin.feature.search.presentation

import androidx.annotation.StringRes
import com.github.dach83.bin.core.domain.model.CardDetails

data class SearchUiState(
    val cardNumber: String,
    val cardDetails: CardDetails,
    val isLoading: Boolean,
    @StringRes val errorMessage: Int?
) {

    fun loading(cardNumber: String) = copy(
        cardNumber = cardNumber,
        cardDetails = CardDetails.EMPTY,
        isLoading = true,
        errorMessage = null
    )

    fun loaded(cardDetails: CardDetails) = copy(
        cardDetails = cardDetails,
        isLoading = false,
        errorMessage = null
    )

    fun error(@StringRes errorMessage: Int) = copy(
        isLoading = false,
        cardDetails = CardDetails.EMPTY,
        errorMessage = errorMessage
    )

    companion object {
        val INITIAL = SearchUiState(
            cardNumber = "",
            cardDetails = CardDetails.EMPTY,
            isLoading = false,
            errorMessage = null
        )
    }
}
