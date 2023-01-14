package com.github.dach83.bin.core.data.local.room

import com.github.dach83.bin.core.data.local.LocalCardDataSource
import com.github.dach83.bin.core.data.local.room.mapper.toCardEntity
import com.github.dach83.bin.core.data.local.room.mapper.toCardQuery
import com.github.dach83.bin.core.data.time.Clock
import com.github.dach83.bin.core.domain.model.CardQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomCardDataSource(
    private val cardDao: CardDao,
    private val clock: Clock
) : LocalCardDataSource {

    override suspend fun saveQuery(cardQuery: CardQuery) {
        val saveTime = clock.currentTime()
        val cardEntity = cardQuery.toCardEntity(saveTime)
        cardDao.insert(cardEntity)
    }

    override fun searchHistory(): Flow<List<CardQuery>> =
        cardDao.searchHistory().map { list ->
            list.map { cardEntity ->
                cardEntity.toCardQuery()
            }
        }
}
