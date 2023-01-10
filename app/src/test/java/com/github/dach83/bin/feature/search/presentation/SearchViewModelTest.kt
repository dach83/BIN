package com.github.dach83.bin.feature.search.presentation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchViewModelTest {

    @Test
    fun check_initial_ui_state() {
        val sut = SearchViewModel()
        val expected = SearchUiState.INITIAL

        val actual = sut.uiState

        assertThat(actual).isEqualTo(expected)
    }
}
