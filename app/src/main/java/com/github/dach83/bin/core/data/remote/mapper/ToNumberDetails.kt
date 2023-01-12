package com.github.dach83.bin.core.data.remote.mapper

import com.github.dach83.bin.core.data.remote.dto.NumberDto
import com.github.dach83.bin.core.domain.model.NumberDetails

fun NumberDto.toNumberDetails() = NumberDetails(
    length = length?.toString() ?: "",
    luhn = luhn?.toYesNo() ?: ""
)
