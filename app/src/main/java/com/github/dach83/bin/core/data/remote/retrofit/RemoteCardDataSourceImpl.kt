package com.github.dach83.bin.core.data.remote.retrofit

import com.github.dach83.bin.R
import com.github.dach83.bin.core.data.remote.RemoteCardDataSource
import com.github.dach83.bin.core.data.remote.retrofit.dto.CardDto
import com.github.dach83.bin.core.data.remote.retrofit.mapper.toCardDetails
import com.github.dach83.bin.core.domain.exception.AppException
import com.github.dach83.bin.core.domain.model.details.CardDetails
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class RemoteCardDataSourceImpl(private val service: BinLookupService) : RemoteCardDataSource {

    override suspend fun cardDetails(cardNumber: String): CardDetails = try {
        val response = service.lookup(cardNumber)
        response.toCardDetails()
    } catch (cause: JsonDataException) {
        throw AppException(R.string.unknown_response_format, cause)
    } catch (cause: UnknownHostException) {
        throw AppException(R.string.no_internet, cause)
    }

    private fun Response<CardDto>.toCardDetails(): CardDetails = if (isSuccessful) {
        val cardDto = body()
        cardDto.toCardDetails()
    } else {
        throwAppException()
    }

    private fun Response<CardDto>.throwAppException(): Nothing {
        when (code()) {
            NO_MATCHING_CARD -> throw AppException(
                R.string.no_matching_card,
                HttpException(this)
            )

            TOO_MANY_REQUEST -> throw AppException(
                R.string.too_many_request,
                HttpException(this)
            )

            else -> throw AppException(
                R.string.service_is_unavailable,
                HttpException(this)
            )
        }
    }

    companion object {
        // If no matching cards are found an HTTP 404 response is returned.
        private const val NO_MATCHING_CARD = 404

        // Requests are throttled at 10 per minute with a burst allowance of 10.
        // If you hit the speed limit the service will return a 429 http status code.
        private const val TOO_MANY_REQUEST = 429
    }
}
