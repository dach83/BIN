package com.github.dach83.bin.core.data.remote.retrofit.mapper

import com.github.dach83.bin.core.data.remote.retrofit.dto.CountryDto
import com.github.dach83.bin.core.domain.model.CountryDetails

fun CountryDto?.toCountryDetails() = if (this == null) {
    CountryDetails.EMPTY
} else {
    CountryDetails(
        numeric = numeric.orEmpty(),
        alpha2 = alpha2.orEmpty(),
        name = name.orEmpty(),
        currency = currency.orEmpty(),
        latitude = latitude?.toString().orEmpty(),
        longitude = longitude?.toString().orEmpty()
    )
}
