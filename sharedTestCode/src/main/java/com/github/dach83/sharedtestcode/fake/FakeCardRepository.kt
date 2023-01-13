package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.domain.model.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.models.*

class FakeCardRepository : CardRepository {
    override suspend fun cardDetails(cardNumber: String): CardDetails {
        return when (cardNumber) {
            CardNumbers.VISA -> visaCardDetails
            CardNumbers.MASTER_CARD -> masterCardDetails
            else -> throw IllegalStateException()
        }
    }
}
