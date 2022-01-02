package com.afauzi.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mWordList: LinkedList<String>
    private var mCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..19) {
            mWordList.addLast("Word " + mCount++)
            Log.d("WordList", mWordList.last)
        }
    }
}