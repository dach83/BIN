package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.model.history.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.models.CardNumbers
import com.github.dach83.sharedtestcode.models.masterCardDetails
import com.github.dach83.sharedtestcode.models.visaCardDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FakeCardRepository : CardRepository {

    override suspend fun cardDetails(cardNumber: String): CardDetails {
        return when (cardNumber) {
            CardNumbers.VISA -> visaCardDetails
            CardNumbers.MASTER_CARD -> masterCardDetails
            else -> throw IllegalStateException()
        }
    }

    override suspend fun saveCard(cardNumber: String) {
        // TODO("Not yet implemented")
    }

    override fun searchHistory(): Flow<List<CardQuery>> {
        // TODO("Not yet implemented")
        return emptyFlow()
    }
}
