package com.github.dach83.sharedtestcode.models

import com.github.dach83.bin.core.domain.model.BankDetails
import com.github.dach83.bin.core.domain.model.CardDetails
import com.github.dach83.bin.core.domain.model.CountryDetails
import com.github.dach83.bin.core.domain.model.NumberDetails

val visaCardDetails = CardDetails(
    number = NumberDetails(
        length = "16",
        luhn = "Yes"
    ),
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
        name = "Jyske Bank",
        url = "www.jyskebank.dk",
        phone = "+4589893300",
        city = "Hjørring"
    )
)

val masterCardDetails = CardDetails(
    number = NumberDetails.EMPTY,
    scheme = "mastercard",
    type = "credit",
    brand = "Standard",
    prepaid = "",
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

// Default values that the user should see on the screen
val emptyCardDetailsOnScreen = CardDetails(
    number = NumberDetails(
        length = "?",
        luhn = "?"
    ),
    scheme = "?",
    type = "?",
    brand = "?",
    prepaid = "?",
    country = CountryDetails(
        numeric = "?",
        alpha2 = "?",
        name = "?",
        currency = "?",
        latitude = "?",
        longitude = "?"
    ),
    bank = BankDetails(
        name = "?",
        url = "?",
        phone = "?",
        city = "?"
    )
)