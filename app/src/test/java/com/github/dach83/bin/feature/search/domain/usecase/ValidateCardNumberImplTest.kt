package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.sharedtestcode.models.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateCardNumberImplTest {

    @Test
    fun `digits card number is valid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.VISA)

        assertThat(isValid).isTrue()
    }

    @Test
    fun `empty card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.EMPTY)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `blank card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.BLANK)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `letters card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.INVALID)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `mixed card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.MIXED)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `too long card number is invalid`() {
        val sut = ValidateCardNumberImpl()

        val isValid = sut(CardNumbers.TOO_LONG)

        assertThat(isValid).isFalse()
    }
}
