package com.github.dach83.bin.core.data.remote.mapper

fun Boolean.toYesNo() = when (this) {
    true -> "Yes"
    false -> "No"
}
