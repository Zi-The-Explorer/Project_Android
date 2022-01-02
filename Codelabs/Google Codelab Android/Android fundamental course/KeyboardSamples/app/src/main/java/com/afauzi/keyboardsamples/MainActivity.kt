package com.afauzi.keyboardsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnShowText(view: View) {

        // metode showText berikut, yang mengambil informasi yang dimasukkan ke dalam elemen EditText dan menampilkannya di dalam pesan toast
        val editText: EditText = findViewById(R.id.editText_main)
        if (editText != null) {
            val showString: String = editText.text.toString()
            Log.d("Input Text: ", showString)
            Toast.makeText(this, showString, Toast.LENGTH_LONG).show()
        }


    }

    fun btnShowTextNumber(view: View) {}
}