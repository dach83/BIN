package com.github.dach83.bin.core.data.remote

import com.github.dach83.bin.R
import com.github.dach83.bin.core.data.remote.dto.CardDto
import com.github.dach83.bin.core.data.remote.mapper.toCardDetails
import com.github.dach83.bin.core.domain.exception.BinException
import com.github.dach83.bin.core.domain.model.CardDetails
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class RemoteCardDataSourceImpl(private val service: BinLookupService) : RemoteCardDataSource {

    override suspend fun cardDetails(cardNumber: String): CardDetails = try {
        service.lookup(cardNumber).toCardDetails()
    } catch (cause: JsonDataException) {
        throw BinException(R.string.unknown_format, cause)
    } catch (cause: UnknownHostException) {
        throw BinException(R.string.no_internet, cause)
    } catch (cause: Exception) {
        throw BinException(R.string.unknown_error, cause)
    }

    private fun Response<CardDto>.toCardDetails(): CardDetails = if (isSuccessful) {
        body()?.toCardDetails() ?: CardDetails.EMPTY
    } else {
        when (code()) {
            NO_MATCHING_CARD -> CardDetails.EMPTY
            HIT_REQUEST_LIMIT -> throw BinException(R.string.binlookup_hit_request_limit)
            else -> throw HttpException(this)
        }
    }

    companion object {
        // If no matching cards are found an HTTP 404 response is returned.
        private const val NO_MATCHING_CARD = 404

        // Requests are throttled at 10 per minute with a burst allowance of 10.
        // If you hit the speed limit the service will return a 429 http status code.
        private const val HIT_REQUEST_LIMIT = 429
    }
}
