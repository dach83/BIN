package com.github.dach83.bin.core.domain.repository

import com.github.dach83.bin.core.domain.model.CardDetails

interface CardRepository {
    suspend fun cardDetails(cardNumber: String): CardDetails
}
