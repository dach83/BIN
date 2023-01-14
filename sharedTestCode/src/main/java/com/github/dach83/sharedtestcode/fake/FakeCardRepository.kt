package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.domain.exception.AppException
import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.core.domain.repository.CardRepository
import com.github.dach83.sharedtestcode.models.CardNumbers
import com.github.dach83.sharedtestcode.models.ERROR_MESSAGE
import com.github.dach83.sharedtestcode.models.masterCardDetails
import com.github.dach83.sharedtestcode.models.visaCardDetails
import kotlinx.coroutines.flow.*

class FakeCardRepository : CardRepository {

    private var error: Exception? = null
    private val queries = mutableListOf<CardQuery>()
    private val historyFlow = MutableSharedFlow<List<CardQuery>>()

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
            else -> throw AppException(ERROR_MESSAGE)
        }
    }

    override suspend fun saveQuery(cardQuery: CardQuery) {
        error?.let { throw it }
        queries.add(cardQuery)
        historyFlow.emit(queries)
    }

    override fun searchHistory(): Flow<List<CardQuery>> = historyFlow

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
