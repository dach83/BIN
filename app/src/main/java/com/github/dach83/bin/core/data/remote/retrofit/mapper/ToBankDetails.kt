package com.github.dach83.bin.core.data.remote.retrofit.mapper

import com.github.dach83.bin.core.data.remote.retrofit.dto.BankDto
import com.github.dach83.bin.core.domain.model.details.BankDetails

fun BankDto?.toBankDetails() = if (this == null) {
    BankDetails.EMPTY
} else {
    BankDetails(
        name = name.orEmpty(),
        url = url.orEmpty(),
        phone = phone.orEmpty(),
        city = city.orEmpty()
    )
}
