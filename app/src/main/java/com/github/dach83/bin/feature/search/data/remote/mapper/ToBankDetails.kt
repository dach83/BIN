package com.github.dach83.bin.feature.search.data.remote.mapper

import com.github.dach83.bin.feature.search.data.remote.dto.BankDto
import com.github.dach83.bin.feature.search.domain.model.BankDetails

fun BankDto.toBankDetails() = BankDetails(
    name = name.orEmpty(),
    url = url.orEmpty(),
    phone = phone.orEmpty(),
    city = city.orEmpty()
)
