package com.github.dach83.bin.core.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.dach83.sharedtestcode.fake.FakeClock
import com.github.dach83.sharedtestcode.models.masterCardQuery
import com.github.dach83.sharedtestcode.models.visaCardQuery
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RoomCardDataSourceTest {

    private lateinit var cardDao: CardDao
    private lateinit var cardDatabase: CardDatabase

    private val clock = FakeClock()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cardDatabase = Room.inMemoryDatabaseBuilder(
            context = context,
            klass = CardDatabase::class.java
        ).build()
        cardDao = cardDatabase.cardDao()
    }

    @After
    fun closeDb() {
        cardDatabase.close()
    }

    @Test
    fun can_save_card_query() = runTest {
        // arrange
        val sut = RoomCardDataSource(cardDao, clock)
        val expected = visaCardQuery

        // act
        sut.saveQuery(visaCardQuery)

        // assert
        val searchHistory = sut.searchHistory().first()
        assertThat(searchHistory).contains(expected)
    }

    @Test
    fun search_history_in_descending_time_order() = runTest {
        // arrange
        val sut = RoomCardDataSource(cardDao, clock)
        val expected = listOf(masterCardQuery, visaCardQuery)

        // act
        clock.currentTime++
        sut.saveQuery(visaCardQuery)
        clock.currentTime++
        sut.saveQuery(masterCardQuery)

        // assert
        val searchHistory = sut.searchHistory().first()
        assertThat(searchHistory).containsExactlyElementsIn(expected).inOrder()
    }
}
