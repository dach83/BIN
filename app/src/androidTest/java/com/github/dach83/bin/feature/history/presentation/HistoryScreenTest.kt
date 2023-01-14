package com.github.dach83.bin.feature.history.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.fake.FakeCardRepository
import com.github.dach83.sharedtestcode.models.CardNumbers
import com.github.dach83.sharedtestcode.models.masterCardQuery
import com.github.dach83.sharedtestcode.models.visaCardQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.get

@OptIn(ExperimentalCoroutinesApi::class)
class HistoryScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var repository: FakeCardRepository

    @Test
    fun search_history_is_displayed() = runTest {
        // arrange
        val expectedNumbers = listOf(
            CardNumbers.VISA_FORMATTED,
            CardNumbers.MASTER_CARD_FORMATTED
        )

        // act
        launchHistoryScreen()
        repository.saveQuery(visaCardQuery)
        repository.saveQuery(masterCardQuery)

        // assert
        assertHistoryScreen(expectedNumbers)
    }

    private fun launchHistoryScreen() {
        composeRule.setContent {
            repository = get<CardRepository>() as FakeCardRepository
            HistoryScreen({})
        }
    }

    private fun assertHistoryScreen(expectedNumbers: List<String>) {
        expectedNumbers.forEach { cardNumber ->
            composeRule.onNodeWithText(cardNumber)
                .assertIsDisplayed()
        }
    }
}
