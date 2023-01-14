package com.github.dach83.bin.core.domain.model

data class CardQuery(
    val cardNumber: String
    // So far, only the card number is here, but in the future
    // we may want to save in local storage the card details as well.
)
