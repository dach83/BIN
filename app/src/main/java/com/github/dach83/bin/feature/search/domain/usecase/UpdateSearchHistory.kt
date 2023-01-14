package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository

class UpdateSearchHistory(private val repository: CardRepository) {

    suspend operator fun invoke(cardQuery: CardQuery) {
        repository.saveQuery(cardQuery)
    }
}
