package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.domain.model.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.models.MASTER_CARD_NUMBER
import com.github.dach83.sharedtestcode.models.VISA_CARD_NUMBER
import com.github.dach83.sharedtestcode.models.masterCardDetails
import com.github.dach83.sharedtestcode.models.visaCardDetails

class FakeCardRepository : CardRepository {
    override suspend fun cardDetails(cardNumber: String): CardDetails {
        return when (cardNumber) {
            VISA_CARD_NUMBER -> visaCardDetails
            MASTER_CARD_NUMBER -> masterCardDetails
            else -> throw IllegalStateException()
        }
    }
}
