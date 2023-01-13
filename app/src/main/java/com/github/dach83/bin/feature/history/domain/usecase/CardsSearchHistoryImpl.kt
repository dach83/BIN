package com.github.dach83.bin.feature.history.domain.usecase

import com.github.dach83.bin.core.domain.model.history.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class CardsSearchHistoryImpl(private val repository: CardRepository) : CardsSearchHistory {
    override fun invoke(): Flow<List<CardQuery>> = repository.searchHistory()
}
