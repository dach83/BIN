package com.github.dach83.bin.feature.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dach83.bin.core.domain.exception.BinException
import com.github.dach83.bin.feature.search.domain.usecase.LoadCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val validateCardNumber: ValidateCardNumber,
    private val loadCardDetails: LoadCardDetails
) : ViewModel() {

    var uiState by mutableStateOf(SearchUiState.INITIAL)
        private set

    private var loadingJob: Job? = null

    fun changeCardNumber(cardNumber: String) {
        when {
            cardNumber.isEmpty() -> restoreInitialState()
            cardNumber.isValid() -> startLoadingJob(cardNumber)
        }
    }

    private fun String.isValid() = validateCardNumber(cardNumber = this)

    private fun restoreInitialState() {
        stopLoadingJob()
        uiState = SearchUiState.INITIAL
    }

    private fun stopLoadingJob() {
        loadingJob?.cancel()
    }

    private fun startLoadingJob(cardNumber: String) {
        stopLoadingJob() // stop previously launched job
        uiState = uiState.loading(cardNumber)
        loadingJob = viewModelScope.launch {
            requestCardDetails(cardNumber)
        }
    }

    private suspend fun requestCardDetails(cardNumber: String) = try {
        delay(WAIT_USER_INPUT_BEFORE_LOAD)
        val cardDetails = loadCardDetails(cardNumber)
        uiState = uiState.loaded(cardDetails)
    } catch (error: BinException) {
        // All incoming errors should be wrapped in a BinException.
        // If we catch another exception, then some unexpected problem
        // has been occurred. In this case, we let the application crash.
        // The sooner we detect the problem, the sooner we can handle it properly.
        uiState = uiState.error(error.friendlyMessage)
    }

    companion object {
        const val WAIT_USER_INPUT_BEFORE_LOAD = 500L
    }
}
