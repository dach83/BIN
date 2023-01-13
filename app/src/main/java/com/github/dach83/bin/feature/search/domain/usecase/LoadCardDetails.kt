package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.CardDetails

interface LoadCardDetails {
    suspend operator fun invoke(cardNumber: String): CardDetails
}
