package com.github.dach83.bin.feature.search.presentation

import com.github.dach83.bin.core.domain.exception.AppException
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.feature.search.*
import com.github.dach83.bin.feature.search.domain.usecase.LoadCardDetails
import com.github.dach83.bin.feature.search.domain.usecase.UpdateSearchHistory
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.bin.rule.CoroutineRule
import com.github.dach83.sharedtestcode.*
import com.github.dach83.sharedtestcode.fake.FakeCardRepository
import com.github.dach83.sharedtestcode.models.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeRepository = FakeCardRepository()

    /**
     * When:
     *
     *    initial state,
     *    input valid card number,
     *    request successful
     *
     * Then:
     *
     *    ui state turn to loading,
     *    then turn to loaded,
     *    request was called
     */
    @Test
    fun `initial state, input valid card number, request successful`() = runTest {
        // assert
        val sut = searchViewModelInInitialState()
        fakeRepository.successMode()

        // act
        sut.changeCardNumber(CardNumbers.VISA)

        // assert
        val loading = SearchUiState.INITIAL.loading(CardNumbers.VISA)
        assertThat(sut.uiState).isEqualTo(loading)

        advanceUntilIdle()
        val loaded = loading.loaded(visaCardDetails)
        assertThat(sut.uiState).isEqualTo(loaded)

        assertThat(fakeRepository.cardDetailsWasCalled).isTrue()
    }

    /**
     * When:
     *
     *    initial state,
     *    user input valid card number,
     *    request failed
     *
     * Then:
     *
     *    ui state turn to loading,
     *    then turn to error,
     *    request was called
     */
    @Test
    fun `initial state, input valid card number, request failed`() = runTest {
        // assert
        val sut = searchViewModelInInitialState()
        fakeRepository.errorMode(AppException(ERROR_MESSAGE))

        // act
        sut.changeCardNumber(CardNumbers.VISA)

        // assert
        val loading = SearchUiState.INITIAL.loading(CardNumbers.VISA)
        assertThat(sut.uiState).isEqualTo(loading)

        advanceUntilIdle()
        val error = loading.error(ERROR_MESSAGE)
        assertThat(sut.uiState).isEqualTo(error)

        assertThat(fakeRepository.cardDetailsWasCalled).isTrue()
    }

    /**
     * When:
     *
     *    initial state,
     *    user input empty card number
     *
     * Then:
     *
     *    ui state not changed,
     *    request not called
     */
    @Test
    fun `initial state, input empty card number`() = runTest {
        // assert
        val sut = searchViewModelInInitialState()
        val expected = sut.uiState

        // act
        sut.changeCardNumber(CardNumbers.EMPTY)
        advanceUntilIdle()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
        advanceUntilIdle()
        assertThat(sut.uiState).isEqualTo(expected)
        assertThat(fakeRepository.cardDetailsWasCalled).isFalse()
    }

    /**
     * When:
     *
     *    initial state,
     *    user input invalid card number
     *
     * Then:
     *
     *    ui state not changed,
     *    request not called
     */
    @Test
    fun `initial state, input invalid card number`() = runTest {
        // assert
        val sut = searchViewModelInInitialState()
        val expected = sut.uiState

        // act
        sut.changeCardNumber(CardNumbers.INVALID)
        advanceUntilIdle()

        // assert
        assertThat(sut.uiState).isEqualTo(expected)
        advanceUntilIdle()
        assertThat(sut.uiState).isEqualTo(expected)
        assertThat(fakeRepository.cardDetailsWasCalled).isFalse()
    }

    /**
     * When:
     *
     *    loaded state,
     *    user input different valid card number,
     *    request successful
     *
     * Then:
     *
     *    ui state turn to loading with new card number,
     *    then turn to loaded,
     *    request was called
     */
    @Test
    fun `loaded state, input different valid card number, request successful`() = runTest {
        // assert
        val sut = searchViewModelInLoadedState(CardNumbers.VISA, visaCardDetails)
        fakeRepository.successMode()

        // act
        sut.changeCardNumber(CardNumbers.MASTER_CARD)

        // assert
        val loading = SearchUiState.INITIAL.loading(CardNumbers.MASTER_CARD)
        assertThat(sut.uiState).isEqualTo(loading)

        advanceUntilIdle()
        val loaded = loading.loaded(masterCardDetails)
        assertThat(sut.uiState).isEqualTo(loaded)

        assertThat(fakeRepository.cardDetailsWasCalled).isTrue()
    }

    /**
     * When:
     *
     *    loaded state,
     *    user input empty card number,
     *    request successful
     *
     * Then:
     *
     *    ui state turn to initial,
     *    request not called
     */
    @Test
    fun `loaded state, input empty card number, request successful`() = runTest {
        // assert
        val sut = searchViewModelInLoadedState(CardNumbers.VISA, visaCardDetails)
        fakeRepository.successMode()

        // act
        sut.changeCardNumber(CardNumbers.EMPTY)

        // assert
        val expected = SearchUiState.INITIAL
        assertThat(sut.uiState).isEqualTo(expected)
        advanceUntilIdle()
        assertThat(sut.uiState).isEqualTo(expected)
        assertThat(fakeRepository.cardDetailsWasCalled).isFalse()
    }

    /**
     * When:
     *
     *    error state,
     *    user input same valid card number,
     *    request successful
     *
     * Then:
     *
     *    ui state turn to loading,
     *    then turn to loaded,
     *    request was called
     */
    @Test
    fun `error state, input same valid card number, request successful`() = runTest {
        // assert
        val sut = searchViewModelInErrorState(CardNumbers.VISA)
        fakeRepository.successMode()

        // act
        sut.changeCardNumber(CardNumbers.VISA)

        // assert
        val loading = SearchUiState.INITIAL.loading(CardNumbers.VISA)
        assertThat(sut.uiState).isEqualTo(loading)

        advanceUntilIdle()
        val loaded = loading.loaded(visaCardDetails)
        assertThat(sut.uiState).isEqualTo(loaded)

        assertThat(fakeRepository.cardDetailsWasCalled).isTrue()
    }

    private fun searchViewModelInInitialState() = SearchViewModel(
        validateCardNumber = ValidateCardNumber(),
        loadCardDetails = LoadCardDetails(fakeRepository),
        updateSearchHistory = UpdateSearchHistory(fakeRepository)
    ).apply {
        assertThat(uiState).isEqualTo(SearchUiState.INITIAL)
    }

    private fun TestScope.searchViewModelInLoadedState(
        cardNumber: String,
        cardDetails: CardDetails
    ) = searchViewModelInInitialState().apply {
        val expected = SearchUiState(
            cardNumber = cardNumber,
            cardDetails = cardDetails,
            isLoading = false,
            errorMessage = null
        )
        fakeRepository.successMode()
        changeCardNumber(cardNumber)
        advanceUntilIdle()
        assertThat(uiState).isEqualTo(expected)
        fakeRepository.restoreInitialState()
    }

    private fun TestScope.searchViewModelInErrorState(
        cardNumber: String
    ) = searchViewModelInInitialState().apply {
        val expected = SearchUiState(
            cardNumber = cardNumber,
            cardDetails = CardDetails.EMPTY,
            isLoading = false,
            errorMessage = ERROR_MESSAGE
        )
        fakeRepository.errorMode(AppException(ERROR_MESSAGE))
        changeCardNumber(cardNumber)
        advanceUntilIdle()
        assertThat(uiState).isEqualTo(expected)
        fakeRepository.restoreInitialState()
    }
}
