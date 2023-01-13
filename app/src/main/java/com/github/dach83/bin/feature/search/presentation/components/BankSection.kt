package com.github.dach83.bin.feature.search.presentation.components

import android.util.Patterns
import androidx.compose.foundation.lazy.LazyListScope
import com.github.dach83.bin.R
import com.github.dach83.bin.core.domain.model.details.BankDetails
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

fun LazyListScope.bankSection(
    bankDetails: BankDetails,
    openUrl: (url: String) -> Unit,
    openPhone: (phone: String) -> Unit
) {
    listHeader(header = R.string.bank_head)
    listItem(
        header = R.string.bank_name,
        text = bankDetails.name,
        testTag = SearchScreenTags.BANK_NAME
    )
    listItem(
        header = R.string.bank_url,
        text = bankDetails.url,
        testTag = SearchScreenTags.BANK_URL,
        isActiveLink = Patterns.WEB_URL.matcher(bankDetails.url).matches(),
        onItemClick = { openUrl(bankDetails.url) }
    )
    listItem(
        header = R.string.bank_phone,
        text = bankDetails.phone,
        testTag = SearchScreenTags.BANK_PHONE,
        isActiveLink = Patterns.PHONE.matcher(bankDetails.phone).matches(),
        onItemClick = { openPhone(bankDetails.phone) }
    )
    listItem(
        header = R.string.bank_city,
        text = bankDetails.city,
        testTag = SearchScreenTags.BANK_CITY
    )
}
