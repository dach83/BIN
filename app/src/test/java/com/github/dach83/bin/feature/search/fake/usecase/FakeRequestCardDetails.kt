package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.exception.SearchException
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.test.*

class FakeRequestCardDetails : RequestCardDetails {

    var wasCalled: Boolean = false
        private set

    private var requestException: Exception? = null

    override suspend fun invoke(cardNumber: String): CardDetails {
        wasCalled = true
        requestException?.let { throw it }
        return when (cardNumber) {
            VISA_CARD_NUMBER -> visaCardDetails
            MASTER_CARD_NUMBER -> masterCardDetails
            else -> throw IllegalStateException()
        }
    }

    fun toSuccessMode() {
        requestException = null
    }

    fun toFailureMode() {
        requestException = SearchException(SEARCH_ERROR_MESSAGE)
    }

    fun resetRequestCounter() {
        wasCalled = false
    }
}
