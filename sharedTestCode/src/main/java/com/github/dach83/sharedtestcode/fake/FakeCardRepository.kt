package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.domain.exception.BinException
import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.models.CardNumbers
import com.github.dach83.sharedtestcode.models.ERROR_MESSAGE
import com.github.dach83.sharedtestcode.models.masterCardDetails
import com.github.dach83.sharedtestcode.models.visaCardDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCardRepository : CardRepository {

    private var error: Exception? = null
    private val queries = mutableListOf<CardQuery>()

    var cardDetailsWasCalled: Boolean = false
        private set

    var saveQueryWasCalled: Boolean = false
        private set

    override suspend fun cardDetails(cardNumber: String): CardDetails {
        cardDetailsWasCalled = true
        error?.let { throw it }
        return when (cardNumber) {
            CardNumbers.VISA -> visaCardDetails
            CardNumbers.MASTER_CARD -> masterCardDetails
            else -> throw BinException(ERROR_MESSAGE)
        }
    }

    override suspend fun saveQuery(cardQuery: CardQuery) {
        error?.let { throw it }
        queries.add(cardQuery)
    }

    override fun searchHistory(): Flow<List<CardQuery>> = flow {
        error?.let { throw it }
        emit(queries)
    }

    fun successMode() {
        this.error = null
    }

    fun errorMode(error: Exception) {
        this.error = error
    }

    fun restoreInitialState() {
        cardDetailsWasCalled = false
        saveQueryWasCalled = false
        successMode()
    }
}
