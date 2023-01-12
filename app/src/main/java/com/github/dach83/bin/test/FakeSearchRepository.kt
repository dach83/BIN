package com.github.dach83.bin.test

import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.repository.SearchRepository

// todo Move to sharedTest
class FakeSearchRepository : SearchRepository {
    override suspend fun getCardDetails(cardNumber: String): CardDetails {
        return when (cardNumber) {
            VISA_CARD_NUMBER -> visaCardDetails
            MASTER_CARD_NUMBER -> masterCardDetails
            else -> throw IllegalStateException()
        }
    }
}
