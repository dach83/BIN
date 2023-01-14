package com.github.dach83.bin.feature.history.domain.usecase

import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class LoadSearchHistory(private val repository: CardRepository) {

    operator fun invoke(): Flow<List<CardQuery>> = repository.searchHistory()
}
