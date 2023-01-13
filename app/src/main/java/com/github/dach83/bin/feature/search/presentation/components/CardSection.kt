package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.cardSection(cardDetails: CardDetails) {
    listHeader(header = R.string.card_head)
    listItem(
        header = R.string.card_scheme,
        text = cardDetails.scheme,
        testTag = SearchScreenTags.CARD_SCHEME
    )
    listItem(
        header = R.string.card_type,
        text = cardDetails.type,
        testTag = SearchScreenTags.CARD_TYPE
    )
    listItem(
        header = R.string.card_brand,
        text = cardDetails.brand,
        testTag = SearchScreenTags.CARD_BRAND
    )
    listItem(
        header = R.string.card_prepaid,
        text = cardDetails.prepaid,
        testTag = SearchScreenTags.CARD_PREPAID
    )
}
