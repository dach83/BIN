package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.countrySection(cardDetails: CardDetails) {
    listHeader(header = R.string.country_head)
    listItem(
        header = R.string.country_numeric,
        cardDetails.country.numeric,
        SearchScreenTags.COUNTRY_NUMERIC
    )
    listItem(
        header = R.string.country_alpha2,
        cardDetails.country.alpha2,
        SearchScreenTags.COUNTRY_ALPHA2
    )
    listItem(
        header = R.string.country_name,
        cardDetails.country.name,
        SearchScreenTags.COUNTRY_NAME
    )
    listItem(
        header = R.string.country_currency,
        cardDetails.country.currency,
        SearchScreenTags.COUNTRY_CURRENCY
    )
    listItem(
        header = R.string.country_latitude,
        cardDetails.country.latitude,
        SearchScreenTags.COUNTRY_LATITUDE
    )
    listItem(
        header = R.string.country_longitude,
        cardDetails.country.longitude,
        SearchScreenTags.COUNTRY_LONGITUDE
    )
}
