package com.github.dach83.bin.feature.search.data.repository

import com.github.dach83.bin.feature.search.data.remote.CardDetailsApi
import com.github.dach83.bin.feature.search.data.remote.mapper.toCardDetails
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val api: CardDetailsApi
) : SearchRepository {

    override suspend fun getCardDetails(cardNumber: String): CardDetails =
        api.getCardDto(cardNumber).toCardDetails()
}
