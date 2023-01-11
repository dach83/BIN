package com.github.dach83.bin.feature.search.presentation

import com.github.dach83.bin.core.rule.CoroutineRule
import com.github.dach83.bin.feature.search.*
import com.github.dach83.bin.feature.search.domain.exception.SearchException
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.fake.usecase.FakeRequestCardDetails
import com.github.dach83.bin.feature.search.fake.usecase.FakeValidateCardNumber
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val validateCardNumber = FakeValidateCardNumber()
    private val requestCardDetails = FakeRequestCardDetails()

    @Test
    fun check_initial_ui_state() {
        // arrange
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = EMPTY_CARD_NUMBER,
            cardDetails = CardDetails(),
            isLoading = false,
            error = null
        )

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    @Test
    fun entering_valid_card_number_updates_ui_state_to_loading() {
        // arrange
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = VALID_CARD_NUMBER,
            isLoading = true,
            error = null
        )

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    @Test
    fun entering_valid_card_number_request_card_details() = runTest {
        // arrange
        val sut = createSearchViewModel()

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)
        runCurrent()

        // assert
        assertThat(requestCardDetails.wasCalled).isTrue()
    }

    @Test
    fun successful_request_card_details_updates_ui_state_to_loaded() = runTest {
        // arrange
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = VALID_CARD_NUMBER,
            cardDetails = visaCardDetails,
            isLoading = false,
            error = null
        )

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)
        runCurrent()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    @Test
    fun entering_invalid_card_number_not_change_ui_state() = runTest {
        // arrange
        val sut = createSearchViewModel()
        sut.changeCardNumber(VALID_CARD_NUMBER)
        runCurrent()
        val expected = sut.uiState

        // act
        sut.changeCardNumber(INVALID_CARD_NUMBER)
        runCurrent()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    @Test
    fun entering_empty_card_number_reset_ui_state_to_initial() = runTest {
        // arrange
        val sut = createSearchViewModel()
        sut.changeCardNumber(VALID_CARD_NUMBER)
        runCurrent()
        val expected = SearchUiState()

        // act
        sut.changeCardNumber(EMPTY_CARD_NUMBER)
        runCurrent()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    @Test
    fun failed_request_card_details_updates_ui_state_to_error() = runTest {
        // arrange
        val requestException = SearchException(SEARCH_ERROR_MESSAGE)
        requestCardDetails.toFailureMode(requestException)
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = VALID_CARD_NUMBER,
            isLoading = false,
            error = SEARCH_ERROR_MESSAGE
        )

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)
        runCurrent()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
    }

    private fun createSearchViewModel() = SearchViewModel(
        validateCardNumber = FakeValidateCardNumber(),
        requestCardDetails = requestCardDetails
    )
}
