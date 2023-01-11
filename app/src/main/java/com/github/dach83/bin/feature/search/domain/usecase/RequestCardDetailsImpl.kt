package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.repository.SearchRepository

class RequestCardDetailsImpl(
    private val searchRepository: SearchRepository
) : RequestCardDetails {
    override suspend fun invoke(cardNumber: String): CardDetails =
        searchRepository.getCardDetails(cardNumber)
}
