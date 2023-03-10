package com.github.dach83.bin.core.domain.model.details

data class CountryDetails(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val currency: String,
    val latitude: String,
    val longitude: String
) {
    companion object {
        val EMPTY = CountryDetails(
            numeric = "",
            alpha2 = "",
            name = "",
            currency = "",
            latitude = "",
            longitude = ""
        )
    }
}
