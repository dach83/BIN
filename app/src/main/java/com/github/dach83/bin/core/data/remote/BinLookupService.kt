package com.github.dach83.bin.core.data.remote

import com.github.dach83.bin.core.data.remote.dto.CardDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinLookupService {

    @GET("{BIN}")
    suspend fun lookup(@Path("BIN") cardBIN: String): Response<CardDto>
}
