package com.github.dach83.bin.core.data.local

import com.github.dach83.bin.core.domain.model.CardDetails

interface LocalCardDataSource {
    suspend fun contains(cardNumber: String): Boolean
    suspend fun saveCard(cardNumber: String, cardDetails: CardDetails)
    suspend fun cardDetails(cardNumber: String): CardDetails
    suspend fun cardNumberList(): List<String>
}
