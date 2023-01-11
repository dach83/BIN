package com.github.dach83.bin.feature.search.data.remote.mapper

fun Boolean.toYesNo() = when (this) {
    true -> "Yes"
    false -> "No"
}
