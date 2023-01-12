package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.sharedtestcode.models.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateCardNumberImplTest {

    @Test
    fun `digits card number is valid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(VISA_CARD_NUMBER)

        assertThat(isValid).isTrue()
    }

    @Test
    fun `empty card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(EMPTY_CARD_NUMBER)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `blank card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(BLANK_CARD_NUMBER)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `letters card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(INVALID_CARD_NUMBER)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `mixed card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(MIXED_CARD_NUMBER)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `too long card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(LONG_CARD_NUMBER)

        assertThat(isValid).isFalse()
    }
}
