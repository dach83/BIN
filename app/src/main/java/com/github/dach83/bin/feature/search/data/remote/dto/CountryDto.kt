package com.github.dach83.bin.feature.search.data.remote.dto

data class CountryDto(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
)
