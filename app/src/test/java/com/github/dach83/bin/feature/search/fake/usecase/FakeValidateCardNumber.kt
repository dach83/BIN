package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.MASTER_CARD_NUMBER
import com.github.dach83.bin.feature.search.VISA_CARD_NUMBER
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber

class FakeValidateCardNumber : ValidateCardNumber {
    override fun invoke(cardNumber: String): Boolean {
        return cardNumber == VISA_CARD_NUMBER || cardNumber == MASTER_CARD_NUMBER
    }
}
