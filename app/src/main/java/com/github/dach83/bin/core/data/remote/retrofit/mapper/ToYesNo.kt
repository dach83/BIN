package com.github.dach83.bin.core.data.remote.retrofit.mapper

fun Boolean.toYesNo() = when (this) {
    true -> "Yes"
    false -> "No"
}
