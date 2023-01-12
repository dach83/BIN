package com.github.dach83.bin.core.domain.model

data class CardDetails(
    val number: NumberDetails,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val country: CountryDetails,
    val bank: BankDetails
) {
    companion object {
        val EMPTY = CardDetails(
            number = NumberDetails.EMPTY,
            scheme = "",
            type = "",
            brand = "",
            prepaid = "",
            country = CountryDetails.EMPTY,
            bank = BankDetails.EMPTY
        )
    }
}
