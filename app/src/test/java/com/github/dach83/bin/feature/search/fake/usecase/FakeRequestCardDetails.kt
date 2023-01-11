package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.visaCardDetails

class FakeRequestCardDetails : RequestCardDetails {

    var wasCalled: Boolean = false
        private set

    private var requestResult: CardDetails = visaCardDetails
    private var requestException: Exception? = null

    override suspend fun invoke(cardNumber: String): CardDetails {
        wasCalled = true
        requestException?.let { throw it }
        return requestResult
    }

    fun toSuccessMode(cardDetails: CardDetails) {
        requestResult = cardDetails
        requestException = null
    }

    fun toFailureMode(exception: Exception) {
        requestException = exception
    }
}
