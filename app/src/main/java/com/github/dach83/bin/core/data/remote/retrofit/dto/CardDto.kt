package com.github.dach83.bin.core.data.remote.retrofit.dto

data class CardDto(
    val number: NumberDto?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryDto?,
    val bank: BankDto?
)
