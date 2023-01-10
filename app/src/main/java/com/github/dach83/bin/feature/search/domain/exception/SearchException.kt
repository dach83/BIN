package com.github.dach83.bin.feature.search.domain.exception

import androidx.annotation.StringRes

class SearchException(
    @StringRes val errorMessage: Int,
    cause: Throwable? = null
) : Exception(cause)
