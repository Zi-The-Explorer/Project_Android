package com.afauzi.layouting

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.afauzi.layouting.layout.Constraint
import com.afauzi.layouting.layout.Relative
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_to_relative_page.setOnClickListener {
            startActivity(Intent(this, Relative::class.java))
        }
        btn_to_constraint_page.setOnClickListener {
            startActivity(Intent(this, Constraint::class.java))
        }

    }
}