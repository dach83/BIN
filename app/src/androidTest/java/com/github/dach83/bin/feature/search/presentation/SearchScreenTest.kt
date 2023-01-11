package com.github.dach83.bin.feature.search.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun initial_state_all_elements_have_default_values() {
        // arrange
        val expectedCardNumber = ""
        val expectedCardDetails = CardDetails.EMPTY

        // act
        launchSearchScreen()

        // assert
        assertSearchScreen(expectedCardNumber, expectedCardDetails)
    }

    private fun assertSearchScreen(
        expectedCardNumber: String,
        expectedCardDetails: CardDetails
    ) {
        composeRule
            .onNodeWithTag(SearchTestTags.INPUT_CARD_NUMBER)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardNumber)

        // scroll to card section
        composeRule
            .onNodeWithTag(SearchTestTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchTestTags.CARD_PREPAID))
        composeRule
            .onNodeWithTag(SearchTestTags.CARD_SCHEME)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.scheme)
        composeRule
            .onNodeWithTag(SearchTestTags.CARD_TYPE)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.type)
        composeRule
            .onNodeWithTag(SearchTestTags.CARD_BRAND)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.brand)
        composeRule
            .onNodeWithTag(SearchTestTags.CARD_PREPAID)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.prepaid)

        // scroll to number section
        composeRule
            .onNodeWithTag(SearchTestTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchTestTags.NUMBER_LUHN))
        composeRule
            .onNodeWithTag(SearchTestTags.NUMBER_LENGTH)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.number.length)
        composeRule
            .onNodeWithTag(SearchTestTags.NUMBER_LUHN)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.number.luhn)

        // scroll to country section
        composeRule
            .onNodeWithTag(SearchTestTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchTestTags.COUNTRY_LONGITUDE))
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_NUMERIC)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.numeric)
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_ALPHA2)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.alpha2)
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_NAME)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.name)
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_CURRENCY)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.currency)
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_LATITUDE)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.latitude)
        composeRule
            .onNodeWithTag(SearchTestTags.COUNTRY_LONGITUDE)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.country.longitude)

        // scroll to bank section
        composeRule
            .onNodeWithTag(SearchTestTags.LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SearchTestTags.BANK_CITY))
        composeRule
            .onNodeWithTag(SearchTestTags.BANK_NAME)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.name)
        composeRule
            .onNodeWithTag(SearchTestTags.BANK_URL)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.url)
        composeRule
            .onNodeWithTag(SearchTestTags.BANK_PHONE)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.phone)
        composeRule
            .onNodeWithTag(SearchTestTags.BANK_CITY)
            .assertIsDisplayed()
            .assertTextEquals(expectedCardDetails.bank.city)
    }

    private fun launchSearchScreen() {
        composeRule.setContent {
            SearchScreen()
        }
    }
}
