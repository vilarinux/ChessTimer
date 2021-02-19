package com.example.chesstimer

import android.icu.util.UniversalTimeScale.toLong
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val SECONDS = 60L
    val MILIS   = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var statusButton = findViewById<TextView>(R.id.statusButton)
        var timers = listOf(findViewById<Chronometer>(R.id.view_timer1),
                            findViewById<Chronometer>(R.id.view_timer2))

        val STATUSES = listOf(getString(R.string.statusButton),getString(R.string.statusButtonReset))
        statusButton.setOnClickListener() {
//            var inputMinutes:Long = (findViewById<TextView>(R.id.minutesInput).toString()).toLong()
            var inputMinutes:Long = 1*SECONDS*MILIS
//            var inputInc     = (findViewById<TextView>(R.id.incInput).toString().toLong())*MILIS

            if (statusButton.text.equals(STATUSES[0])) {
                // start
                statusButton.text = STATUSES[1]

                timers[0].isCountDown = true
                timers[0].base = SystemClock.elapsedRealtime() + inputMinutes
                timers[0].start()
            } else {
                // stop
                statusButton.text = STATUSES[0]
            }
        }
    }

}