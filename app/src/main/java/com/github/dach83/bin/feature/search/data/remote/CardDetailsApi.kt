package com.github.dach83.bin.feature.search.data.remote

import com.github.dach83.bin.feature.search.data.remote.dto.CardDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CardDetailsApi {

    @GET("{bin}")
    suspend fun getCardDto(@Path("bin") cardNumber: String): CardDto
}
