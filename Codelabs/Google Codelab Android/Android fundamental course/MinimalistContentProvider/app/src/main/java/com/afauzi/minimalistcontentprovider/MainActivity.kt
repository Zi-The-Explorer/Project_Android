package com.afauzi.minimalistcontentprovider

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private val TAG = MainActivity::class.java.simpleName
    fun onClickDisplayEntries(view: View?) {
        Log.d(TAG, "Yay, I was clicked!")
    }
}