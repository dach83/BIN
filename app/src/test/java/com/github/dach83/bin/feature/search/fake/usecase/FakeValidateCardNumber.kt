package com.github.dach83.bin.feature.search.fake.usecase

import com.github.dach83.bin.feature.search.domain.usecase.ValidateCardNumber

class FakeValidateCardNumber(private val isValidCardNumber: Boolean) : ValidateCardNumber {
    override fun invoke(cardNumber: String): Boolean {
        return isValidCardNumber
    }
}
