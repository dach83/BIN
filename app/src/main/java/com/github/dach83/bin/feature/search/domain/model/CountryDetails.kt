package com.github.dach83.bin.feature.search.domain.model

data class CountryDetails(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
) {
    companion object {
        val EMPTY = CountryDetails(
            numeric = "?",
            alpha2 = "?",
            name = "?",
            emoji = "?",
            currency = "?",
            latitude = 0,
            longitude = 0
        )
    }
}
