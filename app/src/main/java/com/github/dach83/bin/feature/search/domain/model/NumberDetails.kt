package com.github.dach83.bin.feature.search.domain.model

data class NumberDetails(
    val length: String,
    val luhn: String
) {
    companion object {
        val EMPTY = NumberDetails(
            length = "?",
            luhn = "?"
        )
    }
}
