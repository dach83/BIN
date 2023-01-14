package com.github.dach83.bin.feature.search.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.exception.AppException
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.bin.feature.search.presentation.components.SearchScreenTags
import com.github.dach83.sharedtestcode.fake.FakeCardRepository
import com.github.dach83.sharedtestcode.models.CardNumbers
import com.github.dach83.sharedtestcode.models.emptyCardDetailsOnScreen
import com.github.dach83.sharedtestcode.models.visaCardDetails
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.get

class SearchScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var repository: FakeCardRepository

    @Test
    fun input_empty_card_number_displays_empty_card_details() {
        // arrange
        val expectedCardNumber = CardNumbers.EMPTY
        val expectedCardDetails = emptyCardDetailsOnScreen
        val expectedError = false

        // act
        launchSearchScreen()
        inputCardNumber(CardNumbers.EMPTY)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails, expectedError)
    }

    @Test
    fun input_invalid_card_number_displays_empty_card_details() {
        // arrange
        val expectedCardNumber = CardNumbers.EMPTY
        val expectedCardDetails = emptyCardDetailsOnScreen
        val expectedError = false

        // act
        launchSearchScreen()
        inputCardNumber(CardNumbers.INVALID)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails, expectedError)
    }

    @Test
    fun input_valid_card_number_displays_correct_card_details() {
        // arrange
        val expectedCardNumber = CardNumbers.VISA_FORMATTED
        val expectedCardDetails = visaCardDetails
        val expectedError = false

        // act
        launchSearchScreen()
        inputCardNumber(CardNumbers.VISA)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails, expectedError)
    }

    @Test
    fun in_case_of_failure_displays_error_message() {
        // arrange
        val expectedCardNumber = CardNumbers.VISA_FORMATTED
        val expectedCardDetails = emptyCardDetailsOnScreen
        val expectedError = true

        // act
        launchSearchScreen()
        repository.errorMode(AppException(R.string.no_internet))
        inputCardNumber(CardNumbers.VISA)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails, expectedError)
    }

    private fun launchSearchScreen() {
        composeRule.setContent {
            repository = get<CardRepository>() as FakeCardRepository
            SearchScreen(CardNumbers.EMPTY)
        }
    }

    private fun inputCardNumber(cardNumber: String) {
        composeRule.onNodeWithTag(SearchScreenTags.CARD_NUMBER_EDIT)
            .performTextReplacement(cardNumber)
        waitUntilLoading()
    }

    private fun waitUntilLoading() = composeRule.waitUntil {
        composeRule.onAllNodesWithTag(SearchScreenTags.LOADING_INDICATOR)
            .fetchSemanticsNodes()
            .isEmpty()
    }

    private fun assertSearchScreen(
        expectedCardNumber: String,
        expectedCardDetails: CardDetails,
        expectedError: Boolean
    ) {
        // check error message
        if (expectedError) {
            composeRule.onNodeWithTag(SearchScreenTags.ERROR_MESSAGE)
                .assertIsDisplayed()
        }

        // check card number edit
        composeRule.onNodeWithTag(SearchScreenTags.CARD_NUMBER_EDIT)
            .assertTextEquals(expectedCardNumber)

        // check card section
        composeRule.onNodeWithTag(SearchScreenTags.CARD_SCHEME)
            .assertTextEquals(expectedCardDetails.scheme)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_TYPE)
            .assertTextEquals(expectedCardDetails.type)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_BRAND)
            .assertTextEquals(expectedCardDetails.brand)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_PREPAID)
            .assertTextEquals(expectedCardDetails.prepaid)

        // check number section
        composeRule.onNodeWithTag(SearchScreenTags.NUMBER_LENGTH)
            .assertTextEquals(expectedCardDetails.number.length)
        composeRule.onNodeWithTag(SearchScreenTags.NUMBER_LUHN)
            .assertTextEquals(expectedCardDetails.number.luhn)

        // check country section
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_NUMERIC)
            .assertTextEquals(expectedCardDetails.country.numeric)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_ALPHA2)
            .assertTextEquals(expectedCardDetails.country.alpha2)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_NAME)
            .assertTextEquals(expectedCardDetails.country.name)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_CURRENCY)
            .assertTextEquals(expectedCardDetails.country.currency)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_LATITUDE)
            .assertTextEquals(expectedCardDetails.country.latitude)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_LONGITUDE)
            .assertTextEquals(expectedCardDetails.country.longitude)

        // check bank section
        composeRule.onNodeWithTag(SearchScreenTags.CARD_DETAILS_LIST)
            .performScrollToNode(hasTestTag(SearchScreenTags.BANK_CITY))
        composeRule.onNodeWithTag(SearchScreenTags.BANK_NAME)
            .assertTextEquals(expectedCardDetails.bank.name)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_URL)
            .assertTextEquals(expectedCardDetails.bank.url)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_PHONE)
            .assertTextEquals(expectedCardDetails.bank.phone)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_CITY)
            .assertTextEquals(expectedCardDetails.bank.city)
    }
}
