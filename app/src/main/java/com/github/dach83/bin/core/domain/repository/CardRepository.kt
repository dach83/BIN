package com.github.dach83.bin.core.domain.repository

import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.model.history.CardQuery
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun cardDetails(cardNumber: String): CardDetails
    suspend fun saveCard(cardNumber: String)
    fun searchHistory(): Flow<List<CardQuery>>
}
