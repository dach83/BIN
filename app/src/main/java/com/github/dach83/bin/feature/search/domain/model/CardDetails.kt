package com.github.dach83.bin.feature.search.domain.model

data class CardDetails(
    val number: NumberDetails,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryDetails,
    val bank: BankDetails
) {
    companion object {
        val EMPTY = CardDetails(
            number = NumberDetails.EMPTY,
            scheme = "?",
            type = "?",
            brand = "?",
            prepaid = false,
            country = CountryDetails.EMPTY,
            bank = BankDetails.EMPTY
        )
    }
}
