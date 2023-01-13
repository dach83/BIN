package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.repository.CardRepository

class UpdateSearchHistoryImpl(private val repository: CardRepository) : UpdateSearchHistory {

    override suspend fun invoke(cardNumber: String) {
        repository.saveCard(cardNumber)
    }
}
