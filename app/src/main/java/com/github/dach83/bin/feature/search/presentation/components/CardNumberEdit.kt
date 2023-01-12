package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.presentation.SearchScreenTags

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardNumberEdit(
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(SearchScreenTags.CARD_NUMBER_EDIT)
        )
        Text(
            text = stringResource(R.string.input_card_number_label),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
