package com.afauzi.hellotoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Deklarasi TextView
    private lateinit var showCount: TextView

    // Variabel untuk membuat perhitungan
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menampilkan Toast ketika tombol toast di tekan
        btn_hello_toast.setOnClickListener {
            showToast()
        }

        // Menghitung ketika tombol count di tekan
        btn_count.setOnClickListener {
            showCount = findViewById(R.id.text_view)
            count++
            if (showCount != null) {
                showCount.setText(count.toString())
            }
        }

    }

    fun showToast() {
        Toast.makeText(this, "Hello Toast", Toast.LENGTH_LONG).show()
    }
}