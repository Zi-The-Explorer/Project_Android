package com.afauzi.intentappdicodingcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithDataActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val tvDataReceived: TextView = findViewById(R.id.tv_data_received)

        /**
         * context dibawah ini, key yang digunakan untuk mengirim dan mengambil nilai data adalah sama
         * yaitu EXTRA_NAME (yang bernilai "extra_name") nilai dari data yang dikirimkan melalui intent disimpan kedalam variable name bertipe data string
         *
         */
        val name = intent.getStringExtra(EXTRA_NAME)

        /**
         * Nilai dari variable age yang bertipe data integer berasal dari getIntent().getIntExtra(key, nilai default)
         * key yang digunakan untuk mengirimkan dan mengambil data adalah EXTRA_AGE (yang bernilai "extra_age")
         * Nilai default disini merupakan nilai yang akan digunakan jika ternyata data nya kosong
         * Data kosong atau nilainya null bisa terjadi ketika data nya memang tidak ada, atau key nya tidak sama
         */
        val age = intent.getIntExtra(EXTRA_AGE, 0)

        val text = "Your Name: $name and Your Age: $age"
        tvDataReceived.text = text
    }
}