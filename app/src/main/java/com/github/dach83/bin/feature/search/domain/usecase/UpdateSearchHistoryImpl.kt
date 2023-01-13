package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository

class UpdateSearchHistoryImpl(private val repository: CardRepository) : UpdateSearchHistory {

    override suspend fun invoke(cardQuery: CardQuery) {
        repository.saveQuery(cardQuery)
    }
}
