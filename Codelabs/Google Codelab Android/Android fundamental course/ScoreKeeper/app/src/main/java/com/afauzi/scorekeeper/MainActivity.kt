package com.afauzi.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun decreScore() {
        val btnDecreTeam1 = decreaseTeam1
        val btnDecreTeam2 = decreaseTeam2
        val showScoreTeam1 = score_1

        btnDecreTeam1.setOnClickListener {
            count--
            if (showScoreTeam1 != null) {
                showScoreTeam1.setText(count.toString())
            }
        }
    }

    fun increScore() {

    }

}