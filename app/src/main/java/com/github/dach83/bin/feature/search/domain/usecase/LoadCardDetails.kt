package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository

class LoadCardDetails(private val repository: CardRepository) {

    suspend operator fun invoke(cardNumber: String): CardDetails =
        repository.cardDetails(cardNumber)
}
