package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.cardSection(cardDetails: CardDetails) {
    listHeader(header = R.string.card_head)
    listItem(
        header = R.string.card_scheme,
        cardDetails.scheme,
        SearchScreenTags.CARD_SCHEME
    )
    listItem(
        header = R.string.card_type,
        cardDetails.type,
        SearchScreenTags.CARD_TYPE
    )
    listItem(
        header = R.string.card_brand,
        cardDetails.brand,
        SearchScreenTags.CARD_BRAND
    )
    listItem(
        header = R.string.card_prepaid,
        cardDetails.prepaid,
        SearchScreenTags.CARD_PREPAID
    )
}
