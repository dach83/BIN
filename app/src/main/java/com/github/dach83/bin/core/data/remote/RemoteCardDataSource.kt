package com.github.dach83.bin.core.data.remote

import com.github.dach83.bin.core.domain.model.details.CardDetails

interface RemoteCardDataSource {
    suspend fun cardDetails(cardNumber: String): CardDetails
}
