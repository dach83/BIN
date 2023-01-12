package com.github.dach83.bin.feature.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.domain.exception.SearchException
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val validateCardNumber: ValidateCardNumber,
    private val requestCardDetails: RequestCardDetails
) : ViewModel() {

    var uiState by mutableStateOf(SearchUiState.INITIAL)
        private set

    private var loadingCardDetailsJob: Job? = null

    fun changeCardNumber(cardNumber: String) {
        if (isEmptyCardNumber(cardNumber)) {
            resetToInitialState()
            return
        }

        if (isValidCardNumber(cardNumber)) {
            loadCardDetails(cardNumber)
        }
    }

    private fun isEmptyCardNumber(cardNumber: String): Boolean = cardNumber.isEmpty()

    private fun isValidCardNumber(cardNumber: String): Boolean = validateCardNumber(cardNumber)

    private fun resetToInitialState() {
        uiState = SearchUiState.INITIAL
        loadingCardDetailsJob?.cancel()
    }

    private fun loadCardDetails(cardNumber: String) {
        if (cardDetailsAlreadyLoading(cardNumber)) {
            return
        }

        uiState = uiState.loading(cardNumber)
        loadingCardDetailsJob?.cancel()
        loadingCardDetailsJob = viewModelScope.launch {
            runCatching {
                delay(WAIT_USER_INPUT_BEFORE_REQUEST)
                requestCardDetails(cardNumber)
            }.onSuccess { cardDetails ->
                uiState = uiState.loaded(cardDetails)
            }.onFailure { exception ->
                val errorMessage = errorMessage(exception)
                uiState = uiState.error(errorMessage)
            }
        }
    }

    // Information about a card with this number has been loaded or is loading now
    private fun cardDetailsAlreadyLoading(cardNumber: String): Boolean {
        return uiState.cardNumber == cardNumber && uiState.errorMessage == null
    }

    private fun errorMessage(exception: Throwable): Int =
        if (exception is SearchException) {
            exception.errorMessage
        } else {
            R.string.default_search_error_message
        }

    companion object {
        const val WAIT_USER_INPUT_BEFORE_REQUEST = 500L
    }
}
