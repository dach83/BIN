package com.github.dach83.bin.core.data.remote.mapper

import com.github.dach83.bin.core.data.remote.dto.CountryDto
import com.github.dach83.bin.core.domain.model.CountryDetails

fun CountryDto.toCountryDetails() = CountryDetails(
    numeric = numeric.orEmpty(),
    alpha2 = alpha2.orEmpty(),
    name = name.orEmpty(),
    currency = currency.orEmpty(),
    latitude = latitude?.toString() ?: "",
    longitude = longitude?.toString() ?: ""
)
