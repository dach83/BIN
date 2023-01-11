package com.github.dach83.bin.feature.search.domain.usecase

interface ValidateCardNumber {
    operator fun invoke(cardNumber: String): Boolean
}
