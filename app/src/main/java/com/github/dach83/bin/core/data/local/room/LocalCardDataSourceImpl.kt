package com.github.dach83.bin.core.data.local.room

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.local.room.entity.CardEntity
import com.github.dach83.bin.core.data.local.room.mapper.toCardQuery
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.model.history.CardQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalCardDataSourceImpl(
    private val cardDao: CardDao
) : LocalCardDataSource {
    override suspend fun contains(cardNumber: String): Boolean {
        return false
    }

    override suspend fun saveCard(cardNumber: String) {
        val cardEntity = CardEntity(cardNumber)
        cardDao.insert(cardEntity)
    }

    override suspend fun cardDetails(cardNumber: String): CardDetails {
        TODO("Not yet implemented")
    }

    override fun searchHistory(): Flow<List<CardQuery>> =
        cardDao.searchHistory().map { list ->
            list.map { cardEntity ->
                cardEntity.toCardQuery()
            }
        }
}
