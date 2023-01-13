package com.github.dach83.bin.core.data.repository

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.remote.RemoteCardDataSource
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.model.history.CardQuery
import com.github.dach83.bin.core.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class CardRepositoryImpl(
    private val localDataSource: LocalCardDataSource,
    private val remoteDataSource: RemoteCardDataSource
) : CardRepository {

    override suspend fun cardDetails(cardNumber: String): CardDetails =
        if (localDataSource.contains(cardNumber)) {
            localDataSource.cardDetails(cardNumber)
        } else {
            remoteDataSource.cardDetails(cardNumber)
        }

    override suspend fun saveCard(cardNumber: String) {
        localDataSource.saveCard(cardNumber)
    }

    override fun searchHistory(): Flow<List<CardQuery>> =
        localDataSource.searchHistory()
}
