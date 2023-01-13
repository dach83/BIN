package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.sharedtestcode.models.CardNumbers

class FakeValidateCardNumber : ValidateCardNumber {
    override fun invoke(cardNumber: String): Boolean {
        return cardNumber == CardNumbers.VISA || cardNumber == CardNumbers.MASTER_CARD
    }
}
