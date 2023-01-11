package com.github.dach83.bin.feature.search.data.remote.mapper

import com.github.dach83.bin.feature.search.data.remote.dto.NumberDto
import com.github.dach83.bin.feature.search.domain.model.NumberDetails

fun NumberDto.toNumberDetails() = NumberDetails(
    length = length?.toString() ?: "",
    luhn = luhn?.toYesNo() ?: ""
)
