package com.github.dach83.bin.feature.search.domain.repository

import com.github.dach83.bin.feature.search.domain.model.CardDetails

interface SearchRepository {
    suspend fun requestCardDetails(cardNumber: String): CardDetails
}
