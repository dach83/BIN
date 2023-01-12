package com.github.dach83.bin.feature.search.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.sharedtestcode.EMPTY_CARD_NUMBER
import com.github.dach83.sharedtestcode.VISA_CARD_NUMBER
import com.github.dach83.sharedtestcode.VISA_CARD_NUMBER_FORMATTED
import com.github.dach83.sharedtestcode.visaCardDetails
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun input_empty_card_number_reset_card_details() {
        // arrange
        val expectedCardNumber = EMPTY_CARD_NUMBER
        val expectedCardDetails = CardDetails.EMPTY

        // act
        launchSearchScreen()
        inputCardNumber(EMPTY_CARD_NUMBER)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails)
    }

    @Test
    fun input_valid_card_number_updates_card_details() {
        // arrange
        val expectedCardNumber = VISA_CARD_NUMBER_FORMATTED
        val expectedCardDetails = visaCardDetails

        // act
        launchSearchScreen()
        inputCardNumber(VISA_CARD_NUMBER)

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails)
    }

    private fun launchSearchScreen() {
        composeRule.setContent {
            SearchScreen()
        }
    }

    private fun inputCardNumber(cardNumber: String) {
        composeRule.onNodeWithTag(SearchScreenTags.INPUT_CARD_NUMBER)
            .performTextReplacement(cardNumber)
        waitUntilLoading()
    }

    private fun waitUntilLoading() = composeRule.waitUntilDoesNotExist(
        hasTestTag(SearchScreenTags.LOADING)
    )

    // todo Too long function
    private fun assertSearchScreen(
        expectedCardNumber: String,
        expectedCardDetails: CardDetails
    ) {
        // check card number
        composeRule.onNodeWithTag(SearchScreenTags.INPUT_CARD_NUMBER)
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
        composeRule.onNodeWithTag(SearchScreenTags.LAZY_COLUMN)
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

private const val WAIT_UNTIL_TIMEOUT = 1_000L

private fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}

private fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) = waitUntilNodeCount(matcher, 0, timeoutMillis)
