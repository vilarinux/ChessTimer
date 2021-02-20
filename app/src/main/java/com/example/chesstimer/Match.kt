package com.example.chesstimer

import android.os.SystemClock

val WHITES = 0
val BLACKS = 1

class Match(var players: List<Player>, val increment: Long) {
    var timeDifference: Long = 0L
    fun endPlay(whoIsPlaying: Int) {
        val whoIsWaiting = if (whoIsPlaying == WHITES) BLACKS else WHITES

        this.players[whoIsPlaying].timer.stop()
        this.players[whoIsWaiting].timer.base += SystemClock.elapsedRealtime() - timeDifference + increment
        this.timeDifference = SystemClock.elapsedRealtime()
        this.players[whoIsPlaying].isPlaying = false
        this.players[whoIsPlaying].button.setText(R.string.wait)

        this.players[whoIsWaiting].button.setText(R.string.played)
        this.players[whoIsWaiting].timer.start()

        this.players[whoIsWaiting].isPlaying = true
    }
}