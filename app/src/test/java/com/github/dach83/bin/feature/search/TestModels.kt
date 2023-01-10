package com.github.dach83.bin.feature.search

import com.github.dach83.bin.feature.search.domain.model.CardDetails

const val EMPTY_CARD_NUMBER = ""
const val VALID_CARD_NUMBER = "45717360"
const val INVALID_CARD_NUMBER = "INVALID"
const val SEARCH_ERROR_MESSAGE = 777

val visaCardDetails = CardDetails(
    type = "debit",
    brand = "visa"
)
