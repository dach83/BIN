package com.github.dach83.bin.feature.search.domain.usecase

class ValidateCardNumber {

    operator fun invoke(cardNumber: String): Boolean {
        return isDigitsOnly(cardNumber) && isValidLength(cardNumber)
    }

    private fun isDigitsOnly(cardNumber: String) = cardNumber.all { char ->
        char.isDigit()
    }

    private fun isValidLength(cardNumber: String): Boolean =
        cardNumber.length in 1..MAX_CARD_NUMBER_LENGTH

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
    }
}
