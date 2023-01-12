package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber
import com.github.dach83.sharedtestcode.models.MASTER_CARD_NUMBER
import com.github.dach83.sharedtestcode.models.VISA_CARD_NUMBER

class FakeValidateCardNumber : ValidateCardNumber {
    override fun invoke(cardNumber: String): Boolean {
        return cardNumber == VISA_CARD_NUMBER || cardNumber == MASTER_CARD_NUMBER
    }
}
