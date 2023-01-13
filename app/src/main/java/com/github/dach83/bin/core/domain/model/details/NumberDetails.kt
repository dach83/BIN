package com.github.dach83.bin.core.domain.model.details

data class NumberDetails(
    val length: String,
    val luhn: String
) {
    companion object {
        val EMPTY = NumberDetails(
            length = "",
            luhn = ""
        )
    }
}
