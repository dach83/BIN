package com.github.dach83.bin.feature.search.domain.repository

import com.github.dach83.bin.feature.search.domain.model.BankDetails
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.model.CountryDetails
import com.github.dach83.bin.feature.search.domain.model.NumberDetails

class FakeSearchRepository : SearchRepository {
    override suspend fun getCardDetails(cardNumber: String): CardDetails = CARD_DETAILS

    companion object {
        private val CARD_DETAILS = CardDetails(
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
    }
}
