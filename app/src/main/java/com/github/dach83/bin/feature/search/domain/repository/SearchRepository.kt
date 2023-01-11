package com.github.dach83.bin.feature.search.domain.repository

import com.github.dach83.bin.feature.search.domain.model.CardDetails

interface SearchRepository {
    suspend fun getCardDetails(cardNumber: String): CardDetails
}
