package com.github.dach83.bin.core.data.remote.retrofit

import com.github.dach83.bin.R
import com.github.dach83.bin.core.data.remote.RemoteCardDataSource
import com.github.dach83.bin.core.data.remote.retrofit.dto.CardDto
import com.github.dach83.bin.core.data.remote.retrofit.mapper.toCardDetails
import com.github.dach83.bin.core.domain.exception.BinException
import com.github.dach83.bin.core.domain.model.CardDetails
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class RemoteCardDataSourceImpl(private val service: BinLookupService) : RemoteCardDataSource {

    override suspend fun cardDetails(cardNumber: String): CardDetails = try {
        val response = service.lookup(cardNumber)
        response.toCardDetails()
    } catch (cause: JsonDataException) {
        throw BinException(R.string.binlookup_unknown_response, cause)
    } catch (cause: UnknownHostException) {
        throw BinException(R.string.no_internet, cause)
    }

    private fun Response<CardDto>.toCardDetails(): CardDetails = if (isSuccessful) {
        cardDetailsIfSuccess()
    } else {
        cardDetailsIfHttpError()
    }

    private fun Response<CardDto>.cardDetailsIfSuccess(): CardDetails {
        val cardDto = body()
        return cardDto.toCardDetails()
    }

    private fun Response<CardDto>.cardDetailsIfHttpError(): CardDetails = when (code()) {
        NO_MATCHING_CARD -> CardDetails.EMPTY

        TOO_MANY_REQUEST -> throw BinException(
            R.string.binlookup_too_many_request,
            HttpException(this)
        )

        else -> throw BinException(
            R.string.binlookup_is_unavailable,
            HttpException(this)
        )
    }

    companion object {
        // If no matching cards are found an HTTP 404 response is returned.
        private const val NO_MATCHING_CARD = 404

        // Requests are throttled at 10 per minute with a burst allowance of 10.
        // If you hit the speed limit the service will return a 429 http status code.
        private const val TOO_MANY_REQUEST = 429
    }
}
