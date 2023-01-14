package com.github.dach83.sharedtestcode.fake

import com.github.dach83.bin.core.data.time.Clock

class FakeClock : Clock {

    var currentTime: Long = 0

    override fun currentTime(): Long = currentTime
}
