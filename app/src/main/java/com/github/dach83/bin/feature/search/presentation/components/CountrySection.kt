package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.CountryDetails

fun LazyListScope.countrySection(
    countryDetails: CountryDetails,
    openMap: (latitude: String, longitude: String) -> Unit
) {
    listHeader(header = R.string.country_head)
    listItem(
        header = R.string.country_numeric,
        text = countryDetails.numeric,
        testTag = SearchScreenTags.COUNTRY_NUMERIC
    )
    listItem(
        header = R.string.country_alpha2,
        text = countryDetails.alpha2,
        testTag = SearchScreenTags.COUNTRY_ALPHA2
    )
    listItem(
        header = R.string.country_name,
        text = countryDetails.name,
        testTag = SearchScreenTags.COUNTRY_NAME
    )
    listItem(
        header = R.string.country_currency,
        text = countryDetails.currency,
        testTag = SearchScreenTags.COUNTRY_CURRENCY
    )
    val isValidGeo = countryDetails.latitude.isNotEmpty() && countryDetails.longitude.isNotEmpty()
    listItem(
        header = R.string.country_latitude,
        text = countryDetails.latitude,
        testTag = SearchScreenTags.COUNTRY_LATITUDE,
        isActiveLink = isValidGeo,
        onItemClick = { openMap(countryDetails.latitude, countryDetails.longitude) }
    )
    listItem(
        header = R.string.country_longitude,
        text = countryDetails.longitude,
        testTag = SearchScreenTags.COUNTRY_LONGITUDE,
        isActiveLink = isValidGeo,
        onItemClick = { openMap(countryDetails.latitude, countryDetails.longitude) }
    )
}
