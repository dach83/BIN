package com.github.dach83.bin.feature.search.data.remote.dto

data class CardDto(
    val number: NumberDto?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryDto?,
    val bank: BankDto?
)
