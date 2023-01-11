package com.github.dach83.bin.feature.search.domain.model

data class CardDetails(
    val scheme: String,
    val type: String,
    val brand: String
) {
    companion object {
        val EMPTY = CardDetails(
            scheme = "?",
            type = "?",
            brand = "?"
        )
    }
}
