package com.github.dach83.bin.feature.search.data.remote.mapper

import com.github.dach83.bin.feature.search.data.remote.dto.CardDto
import com.github.dach83.bin.feature.search.domain.model.BankDetails
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.domain.model.CountryDetails
import com.github.dach83.bin.feature.search.domain.model.NumberDetails

fun CardDto.toCardDetails(): CardDetails = CardDetails(
    number = number?.toNumberDetails() ?: NumberDetails.EMPTY,
    scheme = scheme.orEmpty(),
    type = type.orEmpty(),
    brand = brand.orEmpty(),
    prepaid = prepaid?.toYesNo() ?: "",
    country = country?.toCountryDetails() ?: CountryDetails.EMPTY,
    bank = bank?.toBankDetails() ?: BankDetails.EMPTY
)
