package com.github.dach83.bin.feature.search.domain.model

data class NumberDetails(
    val length: Int,
    val luhn: Boolean
) {
    companion object {
        val EMPTY = NumberDetails(
            length = 0,
            luhn = false
        )
    }
}
