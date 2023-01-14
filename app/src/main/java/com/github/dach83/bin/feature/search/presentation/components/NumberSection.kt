package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.NumberDetails

fun LazyListScope.numberSection(numberDetails: NumberDetails) {
    listHeader(header = R.string.number_head)
    listItem(
        header = R.string.number_length,
        text = numberDetails.length,
        testTag = SearchScreenTags.NUMBER_LENGTH
    )
    listItem(
        header = R.string.number_luhn,
        text = numberDetails.luhn,
        testTag = SearchScreenTags.NUMBER_LUHN
    )
}
