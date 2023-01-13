package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.numberSection(cardDetails: CardDetails) {
    listHeader(header = R.string.number_head)
    listItem(
        header = R.string.number_length,
        cardDetails.number.length,
        SearchScreenTags.NUMBER_LENGTH
    )
    listItem(
        header = R.string.number_luhn,
        cardDetails.number.luhn,
        SearchScreenTags.NUMBER_LUHN
    )
}
