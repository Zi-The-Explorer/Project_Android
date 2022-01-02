package com.afauzi.viewviewgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Memberi Judul pada halaman yang di definisikan pada kelas ini
        supportActionBar?.title = "Google Pixel"
        
    }
}