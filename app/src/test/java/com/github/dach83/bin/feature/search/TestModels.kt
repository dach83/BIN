package com.github.dach83.bin.feature.search

import com.github.dach83.bin.feature.search.domain.model.CardDetails

const val EMPTY_CARD_NUMBER = ""
const val BLANK_CARD_NUMBER = "   "
const val VISA_CARD_NUMBER = "45717360"
const val MASTER_CARD_NUMBER = "554386014950"
const val INVALID_CARD_NUMBER = "INVALID"
const val MIXED_CARD_NUMBER = "23,xc]["
const val LONG_CARD_NUMBER = "55438601495012348"
const val SEARCH_ERROR_MESSAGE = 777

val visaCardDetails = CardDetails(
    scheme = "visa",
    type = "debit",
    brand = "Visa/Dankort"
)

val masterCardDetails = CardDetails(
    scheme = "mastercard",
    type = "credit",
    brand = "Standard"
)
