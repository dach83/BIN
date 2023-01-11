package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.VALID_CARD_NUMBER
import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber

class FakeValidateCardNumber() : ValidateCardNumber {
    override fun invoke(cardNumber: String): Boolean {
        return cardNumber == VALID_CARD_NUMBER
    }
}
