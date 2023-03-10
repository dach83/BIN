package com.github.dach83.bin.core.domain.model.details

data class BankDetails(
    val name: String,
    val url: String,
    val phone: String,
    val city: String
) {
    companion object {
        val EMPTY = BankDetails(
            name = "",
            url = "",
            phone = "",
            city = ""
        )
    }
}
