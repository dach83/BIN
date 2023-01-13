package com.github.dach83.bin.core.domain.repository

import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.model.details.CardDetails
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun cardDetails(cardNumber: String): CardDetails
    suspend fun saveQuery(cardQuery: CardQuery)
    fun searchHistory(): Flow<List<CardQuery>>
}
