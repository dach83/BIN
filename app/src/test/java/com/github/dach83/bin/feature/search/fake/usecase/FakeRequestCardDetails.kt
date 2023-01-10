package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails
import com.github.dach83.bin.feature.search.visaCardDetails

class FakeRequestCardDetails(
    private val cardDetails: CardDetails = visaCardDetails,
    private val requestException: Exception? = null
) : RequestCardDetails {

    var wasCalled: Boolean = false
        private set

    override suspend fun invoke(cardNumber: String): CardDetails {
        wasCalled = true
        requestException?.let { throw it }
        return cardDetails
    }
}
