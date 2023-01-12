package com.github.dach83.bin.core.domain.exception

import androidx.annotation.StringRes

class BinException(
    @StringRes val errorMessage: Int, // user friendly error message
    cause: Throwable? = null
) : Exception(cause)
