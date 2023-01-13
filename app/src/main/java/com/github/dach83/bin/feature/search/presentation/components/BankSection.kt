package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.bankSection(cardDetails: CardDetails) {
    listHeader(header = R.string.bank_head)
    listItem(
        header = R.string.bank_name,
        cardDetails.bank.name,
        SearchScreenTags.BANK_NAME
    )
    listItem(
        header = R.string.bank_url,
        cardDetails.bank.url,
        SearchScreenTags.BANK_URL
    )
    listItem(
        header = R.string.bank_phone,
        cardDetails.bank.phone,
        SearchScreenTags.BANK_PHONE
    )
    listItem(
        header = R.string.bank_city,
        cardDetails.bank.city,
        SearchScreenTags.BANK_CITY
    )
}
