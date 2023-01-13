package com.github.dach83.bin.core.data.local.room

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.domain.model.CardDetails

class LocalCardDataSourceImpl : LocalCardDataSource {
    override suspend fun contains(cardNumber: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveCard(cardNumber: String, cardDetails: CardDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun cardDetails(cardNumber: String): CardDetails {
        TODO("Not yet implemented")
    }

    override suspend fun cardNumberList(): List<String> {
        TODO("Not yet implemented")
    }
}
