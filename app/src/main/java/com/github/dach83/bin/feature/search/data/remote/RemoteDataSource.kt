package com.github.dach83.bin.feature.search.data.remote

import com.github.dach83.bin.feature.search.domain.model.CardDetails

interface RemoteDataSource {
    suspend fun getCardDetails(cardNumber: String): CardDetails
}
