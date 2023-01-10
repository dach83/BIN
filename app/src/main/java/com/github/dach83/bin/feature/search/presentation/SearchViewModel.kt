package com.github.dach83.bin.feature.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.domain.exception.SearchException
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(
    private val validateCardNumber: ValidateCardNumber,
    private val requestCardDetails: RequestCardDetails
) : ViewModel() {

    var uiState by mutableStateOf(SearchUiState())
        private set

    private var loadingCardDetailsJob: Job? = null

    fun changeCardNumber(cardNumber: String) {
        if (cardNumber.isEmpty()) {
            resetUiState()
            return
        }

        val isValidCardNumber = validateCardNumber(cardNumber)
        if (isValidCardNumber) {
            loadCardDetails(cardNumber)
        }
    }

    private fun resetUiState() {
        uiState = SearchUiState()
    }

    private fun loadCardDetails(cardNumber: String) {
        uiState = uiState.copy(
            cardNumber = cardNumber,
            isLoading = true,
            error = null
        )

        loadingCardDetailsJob?.cancel()
        loadingCardDetailsJob = viewModelScope.launch {
            runCatching {
                requestCardDetails(cardNumber)
            }.onSuccess { cardDetails ->
                uiState = uiState.copy(
                    cardDetails = cardDetails,
                    isLoading = false,
                    error = null
                )
            }.onFailure { exception ->
                uiState = uiState.copy(
                    cardDetails = CardDetails(),
                    isLoading = false,
                    error = errorMessage(exception)
                )
            }
        }
    }

    private fun errorMessage(exception: Throwable): Int =
        if (exception is SearchException) {
            exception.errorMessage
        } else {
            R.string.default_search_error_message
        }
}
