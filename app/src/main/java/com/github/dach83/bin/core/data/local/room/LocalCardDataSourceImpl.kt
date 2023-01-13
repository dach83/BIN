package com.github.dach83.bin.core.data.local.room

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.local.room.entity.CardEntity
import com.github.dach83.bin.core.domain.model.CardDetails

class LocalCardDataSourceImpl(
    private val dao: CardDatabase
) : LocalCardDataSource {
    override suspend fun contains(cardNumber: String): Boolean {
        return false
    }

    override suspend fun saveCard(cardNumber: String, cardDetails: CardDetails) {
        val cardEntity = CardEntity(cardNumber)
        dao.cardDao().insert(cardEntity)
    }

    override suspend fun cardDetails(cardNumber: String): CardDetails {
        TODO("Not yet implemented")
    }
}
