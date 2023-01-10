package com.github.dach83.bin.feature.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val isValidCardNumber = validateCardNumber(cardNumber)
        if (isValidCardNumber) {
            loadCardDetails(cardNumber)
        }
    }

    private fun loadCardDetails(cardNumber: String) {
        uiState = uiState.copy(
            cardNumber = cardNumber,
            isLoading = true
        )

        loadingCardDetailsJob?.cancel()
        loadingCardDetailsJob = viewModelScope.launch {
            val cardDetails = requestCardDetails(cardNumber)
            uiState = uiState.copy(
                cardDetails = cardDetails,
                isLoading = false
            )
        }
    }
}
