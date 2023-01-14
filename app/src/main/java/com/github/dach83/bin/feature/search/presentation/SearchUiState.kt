package com.github.dach83.bin.feature.search.presentation

import androidx.annotation.StringRes
import com.github.dach83.bin.core.domain.model.details.CardDetails

data class SearchUiState(
    val cardNumber: String,
    val cardDetails: CardDetails,
    val isLoading: Boolean,
    @StringRes val errorMessage: Int? = null
) {

    fun loading(cardNumber: String) = copy(
        cardNumber = cardNumber,
        cardDetails = CardDetails.EMPTY,
        isLoading = true
    )

    fun loaded(cardDetails: CardDetails) = copy(
        cardDetails = cardDetails,
        isLoading = false,
        errorMessage = null
    )

    fun error(@StringRes errorMessage: Int) = copy(
        cardDetails = CardDetails.EMPTY,
        isLoading = false,
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
