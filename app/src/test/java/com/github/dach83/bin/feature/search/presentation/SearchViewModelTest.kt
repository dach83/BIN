package com.github.dach83.bin.feature.search.presentation

import com.github.dach83.bin.core.rule.CoroutineRule
import com.github.dach83.bin.feature.search.INVALID_CARD_NUMBER
import com.github.dach83.bin.feature.search.VALID_CARD_NUMBER
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.bin.feature.search.fake.usecase.FakeRequestCardDetails
import com.github.dach83.bin.feature.search.fake.usecase.FakeValidateCardNumber
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun check_initial_ui_state() {
        // arrange
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = "",
            isLoading = false,
            error = null
        )

        // act
        val actual = sut.uiState

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun entering_invalid_card_number_not_change_ui_state() {
        // arrange
        val validateCardNumber = FakeValidateCardNumber(false)
        val sut = createSearchViewModel(
            validateCardNumber = validateCardNumber
        )
        val expected = sut.uiState

        // act
        sut.changeCardNumber(INVALID_CARD_NUMBER)
        val actual = sut.uiState

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun entering_valid_card_number_updates_ui_state_to_loading() {
        // arrange
        val sut = createSearchViewModel()
        val expected = SearchUiState(
            cardNumber = VALID_CARD_NUMBER,
            isLoading = true
        )

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)
        val actual = sut.uiState

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun entering_valid_card_number_request_card_details() {
        // arrange
        val requestCardDetails = FakeRequestCardDetails()
        val sut = createSearchViewModel(
            requestCardDetails = requestCardDetails
        )

        // act
        sut.changeCardNumber(VALID_CARD_NUMBER)
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // assert
        assertThat(requestCardDetails.wasCalled).isTrue()
    }

    private fun createSearchViewModel(
        validateCardNumber: ValidateCardNumber = FakeValidateCardNumber(isValidCardNumber = true),
        requestCardDetails: RequestCardDetails = FakeRequestCardDetails()
    ) = SearchViewModel(
        validateCardNumber = validateCardNumber,
        requestCardDetails = requestCardDetails
    )
}
