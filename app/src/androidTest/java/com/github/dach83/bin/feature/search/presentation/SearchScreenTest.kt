package com.github.dach83.bin.feature.search.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.test.EMPTY_CARD_NUMBER
import com.github.dach83.bin.test.VISA_CARD_NUMBER
import com.github.dach83.bin.test.VISA_CARD_NUMBER_FORMATTED
import com.github.dach83.bin.test.visaCardDetails
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

    private fun assertSearchScreen(
        expectedCardNumber: String,
        expectedCardDetails: CardDetails
    ) {
        // check card number
        composeRule.onNodeWithTag(SearchScreenTags.INPUT_CARD_NUMBER).assertIsDisplayed()
            .assertTextEquals(expectedCardNumber)

        // check card section
        composeRule.onNodeWithTag(SearchScreenTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchScreenTags.CARD_PREPAID))
        composeRule.onNodeWithTag(SearchScreenTags.CARD_SCHEME).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.scheme)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_TYPE).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.type)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_BRAND).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.brand)
        composeRule.onNodeWithTag(SearchScreenTags.CARD_PREPAID).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.prepaid)

        // check number section
        composeRule.onNodeWithTag(SearchScreenTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchScreenTags.NUMBER_LUHN))
        composeRule.onNodeWithTag(SearchScreenTags.NUMBER_LENGTH).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.number.length)
        composeRule.onNodeWithTag(SearchScreenTags.NUMBER_LUHN).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.number.luhn)

        // check country section
        composeRule.onNodeWithTag(SearchScreenTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchScreenTags.COUNTRY_LONGITUDE))
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_NUMERIC).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.numeric)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_ALPHA2).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.alpha2)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_NAME).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.name)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_CURRENCY).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.currency)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_LATITUDE).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.latitude)
        composeRule.onNodeWithTag(SearchScreenTags.COUNTRY_LONGITUDE).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.longitude)

        // check bank section
        composeRule.onNodeWithTag(SearchScreenTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchScreenTags.BANK_CITY))
        composeRule.onNodeWithTag(SearchScreenTags.BANK_NAME).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.name)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_URL).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.url)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_PHONE).assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.phone)
        composeRule.onNodeWithTag(SearchScreenTags.BANK_CITY).assertIsDisplayed()
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

private fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) = waitUntilNodeCount(matcher, 1, timeoutMillis)

private fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) = waitUntilNodeCount(matcher, 0, timeoutMillis)
