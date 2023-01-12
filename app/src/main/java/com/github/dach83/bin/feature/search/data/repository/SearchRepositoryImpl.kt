package com.github.dach83.bin.feature.search.data.repository

import com.github.dach83.bin.feature.search.data.remote.RemoteDataSource
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remote: RemoteDataSource
) : SearchRepository {

    override suspend fun getCardDetails(cardNumber: String): CardDetails {
        return remote.getCardDetails(cardNumber)
    }
}
