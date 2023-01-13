package com.github.dach83.bin.core.data.remote.retrofit

import com.github.dach83.bin.core.domain.exception.BinException
import com.github.dach83.bin.core.domain.model.CardDetails
import com.github.dach83.sharedtestcode.models.*
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteCardDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: BinLookupService
    private val client = OkHttpClient.Builder().build()
    private val moshi: Moshi = Moshi.Builder().build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(BinLookupService::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `correct response returns correct card details`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(visaCardResponse)
            .setResponseCode(200)
        mockWebServer.enqueue(response)
        val sut = RemoteCardDataSourceImpl(service)
        val expected = visaCardDetails

        // act
        val actual = sut.cardDetails(CardNumbers.VISA)

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `404 response (no matching cards) returns empty card details`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(visaCardResponse)
            .setResponseCode(404)
        mockWebServer.enqueue(response)
        val sut = RemoteCardDataSourceImpl(service)
        val expected = CardDetails.EMPTY

        // act
        val actual = sut.cardDetails(CardNumbers.VISA)

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = BinException::class)
    fun `incorrect response throw BinException`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(200)
        mockWebServer.enqueue(response)
        val sut = RemoteCardDataSourceImpl(service)

        // act
        sut.cardDetails(CardNumbers.VISA)
    }

    @Test(expected = BinException::class)
    fun `error response throw BinException`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(400)
        mockWebServer.enqueue(response)
        val sut = RemoteCardDataSourceImpl(service)

        // act
        sut.cardDetails(CardNumbers.VISA)
    }
}
