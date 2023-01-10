package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.usecase.RequestCardDetails

class FakeRequestCardDetails(
    private val cardDetails: CardDetails = CardDetails()
) : RequestCardDetails {

    var wasCalled: Boolean = false
        private set

    override suspend fun invoke(cardNumber: String): CardDetails {
        wasCalled = true
        return cardDetails
    }
}
