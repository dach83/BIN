package com.github.dach83.bin.core.data.remote.retrofit.mapper

import com.github.dach83.bin.core.data.remote.retrofit.dto.CardDto
import com.github.dach83.bin.core.domain.model.CardDetails

fun CardDto?.toCardDetails(): CardDetails = if (this == null) {
    CardDetails.EMPTY
} else {
    CardDetails(
        number = number.toNumberDetails(),
        scheme = scheme.orEmpty(),
        type = type.orEmpty(),
        brand = brand.orEmpty(),
        prepaid = prepaid?.toYesNo().orEmpty(),
        country = country.toCountryDetails(),
        bank = bank.toBankDetails()
    )
}
