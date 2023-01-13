package com.github.dach83.bin.feature.search.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags
import com.github.dach83.bin.feature.search.presentation.SearchUiState

@Composable
fun CardDetailsList(uiState: SearchUiState) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.testTag(SearchScreenTags.CARD_DETAILS_LIST)
    ) {
        cardSection(uiState.cardDetails)
        numberSection(uiState.cardDetails.number)
        countrySection(
            countryDetails = uiState.cardDetails.country,
            openMap = context::openMap
        )
        bankSection(
            bankDetails = uiState.cardDetails.bank,
            openUrl = context::openUrl,
            openPhone = context::openPhone
        )
    }
}

private fun Context.openUrl(url: String) = startActivity(
    Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
)

private fun Context.openPhone(phone: String) = startActivity(
    Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
)

private fun Context.openMap(latitude: String, longitude: String) = startActivity(
    Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
)
