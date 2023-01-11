package com.github.dach83.bin.feature.search.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel()) {
    val uiState = viewModel.uiState
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        CardNumberTextField(cardNumber = uiState.cardNumber, onValueChange = { newCardNumber ->
            viewModel.changeCardNumber(newCardNumber)
        })
        if (uiState.errorMessage != null) {
            Text(text = stringResource(id = uiState.errorMessage))
        }
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(modifier = Modifier.testTag(SearchScreenTags.LAZY_COLUMN)) {
            card(uiState.cardDetails)
            number(uiState.cardDetails)
            country(uiState.cardDetails)
            bank(uiState.cardDetails)
        }
    }
    if (uiState.isLoading) {
        // inserting an invisible box in order to in tests it was
        // possible to determine the time of the end of the loading
        Box(modifier = Modifier.testTag(SearchScreenTags.LOADING))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CardNumberTextField(
    cardNumber: String,
    onValueChange: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = cardNumber,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            visualTransformation = { cardNumber ->
                formatCardNumber(cardNumber)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(SearchScreenTags.INPUT_CARD_NUMBER)
        )
        Text(
            text = stringResource(R.string.input_card_number_label),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

private fun LazyListScope.card(cardDetails: CardDetails) {
    header(header = R.string.card_head)
    detail(
        header = R.string.card_scheme,
        cardDetails.scheme,
        SearchScreenTags.CARD_SCHEME
    )
    detail(
        header = R.string.card_type,
        cardDetails.type,
        SearchScreenTags.CARD_TYPE
    )
    detail(
        header = R.string.card_brand,
        cardDetails.brand,
        SearchScreenTags.CARD_BRAND
    )
    detail(
        header = R.string.card_prepaid,
        cardDetails.prepaid,
        SearchScreenTags.CARD_PREPAID
    )
}

private fun LazyListScope.number(cardDetails: CardDetails) {
    header(header = R.string.number_head)
    detail(
        header = R.string.number_length,
        cardDetails.number.length,
        SearchScreenTags.NUMBER_LENGTH
    )
    detail(
        header = R.string.number_luhn,
        cardDetails.number.luhn,
        SearchScreenTags.NUMBER_LUHN
    )
}

private fun LazyListScope.country(cardDetails: CardDetails) {
    header(header = R.string.country_head)
    detail(
        header = R.string.country_numeric,
        cardDetails.country.numeric,
        SearchScreenTags.COUNTRY_NUMERIC
    )
    detail(
        header = R.string.country_alpha2,
        cardDetails.country.alpha2,
        SearchScreenTags.COUNTRY_ALPHA2
    )
    detail(
        header = R.string.country_name,
        cardDetails.country.name,
        SearchScreenTags.COUNTRY_NAME
    )
    detail(
        header = R.string.country_currency,
        cardDetails.country.currency,
        SearchScreenTags.COUNTRY_CURRENCY
    )
    detail(
        header = R.string.country_latitude,
        cardDetails.country.latitude,
        SearchScreenTags.COUNTRY_LATITUDE
    )
    detail(
        header = R.string.country_longitude,
        cardDetails.country.longitude,
        SearchScreenTags.COUNTRY_LONGITUDE
    )
}

private fun LazyListScope.bank(cardDetails: CardDetails) {
    header(header = R.string.bank_head)
    detail(
        header = R.string.bank_name,
        cardDetails.bank.name,
        SearchScreenTags.BANK_NAME
    )
    detail(
        header = R.string.bank_url,
        cardDetails.bank.url,
        SearchScreenTags.BANK_URL
    )
    detail(
        header = R.string.bank_phone,
        cardDetails.bank.phone,
        SearchScreenTags.BANK_PHONE
    )
    detail(
        header = R.string.bank_city,
        cardDetails.bank.city,
        SearchScreenTags.BANK_CITY
    )
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.header(@StringRes header: Int) {
    stickyHeader {
        Text(
            text = stringResource(id = header).toUpperCase(Locale.current),
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

private fun LazyListScope.detail(
    @StringRes header: Int,
    text: String,
    testTag: String
) {
    item {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = header).toLowerCase(Locale.current)
            )
            Text(
                text = text,
                modifier = Modifier.testTag(testTag)
            )
        }
    }
}

// Format card number in 1111 2222 3333 4444
fun formatCardNumber(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 16) {
        text.text.substring(0..15)
    } else {
        text.text
    }

    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += " "
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}
