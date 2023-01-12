package com.github.dach83.bin.feature.search.data.remote

import com.github.dach83.bin.R
import com.github.dach83.bin.feature.search.data.remote.mapper.toCardDetails
import com.github.dach83.bin.feature.search.domain.exception.SearchException
import com.github.dach83.bin.feature.search.domain.model.CardDetails
import com.squareup.moshi.JsonDataException
import java.net.UnknownHostException

class RemoteDataSourceImpl(private val service: BinLookupService) : RemoteDataSource {

    override suspend fun getCardDetails(cardNumber: String): CardDetails = try {
        val response = service.lookup(cardNumber)
        if (response.isSuccessful) {
            response.body()?.toCardDetails() ?: CardDetails.EMPTY
        } else {
            when (response.code()) {
                NO_MATCHING_CARD -> CardDetails.EMPTY
                HIT_REQUEST_LIMIT -> throw SearchException(R.string.binlookup_hit_request_limit)
                else -> throw IllegalStateException("Unknown BinLookup Service response code: ${response.code()}")
            }
        }
    } catch (cause: SearchException) {
        throw cause
    } catch (cause: JsonDataException) {
        throw SearchException(R.string.unknown_format, cause)
    } catch (cause: UnknownHostException) {
        throw SearchException(R.string.no_internet, cause)
    } catch (cause: Exception) {
        throw SearchException(R.string.unknown_error, cause)
    }

    companion object {
        private const val NO_MATCHING_CARD = 404
        private const val HIT_REQUEST_LIMIT = 429
    }
}
