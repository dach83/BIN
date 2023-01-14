package com.github.dach83.bin.core.data.local

import com.github.dach83.bin.core.domain.model.CardQuery
import kotlinx.coroutines.flow.Flow

interface LocalCardDataSource {
    suspend fun saveQuery(cardQuery: CardQuery)
    fun searchHistory(): Flow<List<CardQuery>>
}
