package com.github.dach83.bin.core.data.time

import java.util.Date

class AndroidClock : Clock {

    override fun currentTime(): Long = Date().time
}
