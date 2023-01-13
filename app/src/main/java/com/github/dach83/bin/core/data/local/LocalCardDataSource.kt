package com.github.dach83.bin.core.data.local

import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.model.history.CardQuery
import kotlinx.coroutines.flow.Flow

interface LocalCardDataSource {
    suspend fun contains(cardNumber: String): Boolean
    suspend fun saveCard(cardNumber: String)
    suspend fun cardDetails(cardNumber: String): CardDetails
    fun searchHistory(): Flow<List<CardQuery>>
}
