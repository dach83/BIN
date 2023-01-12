package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository

class RequestCardDetailsImpl(
    private val cardRepository: CardRepository
) : RequestCardDetails {
    override suspend fun invoke(cardNumber: String): CardDetails =
        cardRepository.cardDetails(cardNumber)
}
