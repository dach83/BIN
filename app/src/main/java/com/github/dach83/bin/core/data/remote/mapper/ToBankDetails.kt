package com.github.dach83.bin.core.data.remote.mapper

import com.github.dach83.bin.core.data.remote.dto.BankDto
import com.github.dach83.bin.core.domain.model.BankDetails

fun BankDto.toBankDetails() = BankDetails(
    name = name.orEmpty(),
    url = url.orEmpty(),
    phone = phone.orEmpty(),
    city = city.orEmpty()
)
