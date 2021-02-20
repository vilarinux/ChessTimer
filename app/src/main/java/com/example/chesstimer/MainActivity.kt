package com.example.chesstimer

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val WHITES = 0
    val BLACKS = 1
    val SECONDS = 60L
    val MILIS   = 1000L
    val EXTRA_TIME = 200L
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var statusButton = findViewById<TextView>(R.id.statusButton)
        var players = listOf(Player(findViewById<Button>(R.id.statusPlayer1), findViewById<Chronometer>(R.id.view_timer1)),
                                     Player(findViewById<Button>(R.id.statusPlayer2), findViewById<Chronometer>(R.id.view_timer2)))

        val STATUSES = listOf(getString(R.string.statusButton),getString(R.string.statusButtonReset))

        statusButton.setOnClickListener() {
            val inputMinutes:Long = (findViewById<TextView>(R.id.minutesInput)).toString().toLong()*SECONDS*MILIS
//            var inputMinutes:Long = SECONDS*MILIS + EXTRA_TIME
//            var inputInc     = (findViewById<TextView>(R.id.incInput).toString().toLong())*MILIS

            if (statusButton.text.equals(STATUSES[0])) {
                // start
                statusButton.text = STATUSES[1]

                players.forEach {
                    it.timer.isCountDown = true
                    it.timer.base = SystemClock.elapsedRealtime() + inputMinutes
                }

                var match = Match(players)
                players[WHITES].button.setText(R.string.played)
                players[WHITES].timer.start()

                match.timeDifference = SystemClock.elapsedRealtime()

                players[WHITES].button.setOnClickListener() {
                    if (players[WHITES].isPlaying)
                        match.endPlay(WHITES)
                }

                players[BLACKS].button.setOnClickListener() {
                    if (players[BLACKS].isPlaying)
                        match.endPlay(BLACKS)
                }

            } else {
                // stop
                statusButton.text = STATUSES[0]
                players.forEach {it.timer.stop()}
            }
        }
    }

}