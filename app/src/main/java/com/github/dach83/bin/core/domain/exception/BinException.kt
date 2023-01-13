package com.github.dach83.bin.core.domain.exception

import androidx.annotation.StringRes

class BinException(
    @StringRes val friendlyMessage: Int, // user friendly error message
    cause: Throwable? = null
) : Exception(cause)
