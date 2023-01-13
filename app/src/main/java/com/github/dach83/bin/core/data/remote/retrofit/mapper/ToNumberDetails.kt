package com.github.dach83.bin.core.data.remote.retrofit.mapper

import com.github.dach83.bin.core.data.remote.retrofit.dto.NumberDto
import com.github.dach83.bin.core.domain.model.NumberDetails

fun NumberDto?.toNumberDetails() = if (this == null) {
    NumberDetails.EMPTY
} else {
    NumberDetails(
        length = length?.toString().orEmpty(),
        luhn = luhn?.toYesNo().orEmpty()
    )
}
