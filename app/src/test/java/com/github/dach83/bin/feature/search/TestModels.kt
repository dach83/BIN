package com.github.dach83.bin.feature.search

import com.github.dach83.bin.feature.search.domain.model.BankDetails
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.model.CountryDetails
import com.github.dach83.bin.feature.search.domain.model.NumberDetails

const val EMPTY_CARD_NUMBER = ""
const val BLANK_CARD_NUMBER = "   "
const val VISA_CARD_NUMBER = "45717360"
const val MASTER_CARD_NUMBER = "554386014950"
const val INVALID_CARD_NUMBER = "INVALID"
const val MIXED_CARD_NUMBER = "23,xc]["
const val LONG_CARD_NUMBER = "55438601495012348"
const val SEARCH_ERROR_MESSAGE = 777

val visaCardDetails = CardDetails(
    number = NumberDetails.EMPTY,
    scheme = "visa",
    type = "debit",
    brand = "Visa/Dankort",
    prepaid = "No",
    country = CountryDetails(
        numeric = "208",
        alpha2 = "DK",
        name = "Denmark",
        currency = "DKK",
        latitude = "56",
        longitude = "10"
    ),
    bank = BankDetails(
        name = "Jyske Ban",
        url = "www.jyskebank.dk",
        phone = "+4589893300",
        city = "Hjørring"
    )
)

val masterCardDetails = CardDetails(
    number = NumberDetails(
        length = "16",
        luhn = "Yes"
    ),
    scheme = "mastercard",
    type = "credit",
    brand = "Standard",
    prepaid = "No",
    country = CountryDetails(
        numeric = "643",
        alpha2 = "RU",
        name = "Russian Federation",
        currency = "RUB",
        latitude = "60",
        longitude = "100"
    ),
    bank = BankDetails(
        name = "VTB24",
        url = "www.vtb.com",
        phone = "(800) 100-24-24",
        city = ""
    )
)
