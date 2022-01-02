package com.afauzi.androidfundamentaldicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Class dibawah ini Menandakan bahwa kelas Java di bawah merupakan sebuah activity karena inherit (turunan) dari superclass bernama AppCompatActivity.
 *
 * View.OnClickListener -> Ini adalah listener yang kita implementasikan untuk memantau kejadian klik pada komponen tombol (button).
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    /**
     * Perhatikan metode onSaveInstanceState.
     * Di dalam metode tersebut, hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya.
     * Untuk menyimpan data di sini menggunakan konsep key-value, dengan STATE_RESULT sebagai key dan isi dari tvResult sebagai value.
     * Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur. Di sini kita perlu menambahkan onSaveInstanceState karena ketika orientasi berubah, activity tersebut akan di-destroy dan memanggil fungsi onCreate lagi,
     * sehingga kita perlu menyimpan nilai hasil perhitungan tersebut supaya data tetap terjaga dan tidak hilang ketika orientasi berubah.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, textViewResult.text.toString())
    }

    /**
     * Ini beberapa variabel untuk menampung view
     */
    private lateinit var editTextWidt: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var editTextLength: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView
    /**
     * Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
     * Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas.
     */

    /**
     * Metode onCreate() merupakan metode utama pada activity. Di sinilah kita dapat mengatur layout xml.
     * Semua proses inisialisasi komponen yang digunakan akan dijalankan di sini.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Inisialisasi variable yang telah dibuat
         */

        /**
         *  Inisialisasi komponen dengan findViewById berfungsi untuk menghubungkan variabel yang kita buat sebelumnya dengan id yang sudah kita buat di dalam layout activity_main.xml.
         */
        editTextWidt = findViewById(R.id.et_width) // Maksud dari baris disamping adalah objek edittext edtWidth disesuaikan (cast) dengan komponen edittext ber-id edt_width di layout xml melalui metode findViewById().
        editTextHeight = findViewById(R.id.et_height)
        editTextLength = findViewById(R.id.et_length)
        buttonCalculate = findViewById(R.id.btn_calculate)
        textViewResult = findViewById(R.id.tv_result)

        /**
         * Kita memasang event click listener untuk objek btnCalculate sehingga sebuah aksi dapat dijalankan ketika objek tersebut diklik.
         * Keyword this merujuk pada objek Activity saat ini yang telah mengimplementasikan listener OnClickListener sebelumnya.
         * Sehingga ketika btnCalculate diklik, maka fungsi onClick akan dipanggil dan melakukan proses yang ada di dalamnya.
         */
        buttonCalculate.setOnClickListener(this)

        /**
         * Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan sebelumnya pada onSaveInstanceState.
         * Nilai tersebut kita dapatkan dengan menggunakan key yang sama dengan saat menyimpan, yaitu STATE_RESULT.
         * Kemudian kita isikan kembali pada tvResult.
         */
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            textViewResult.text = result
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {

            /**
             * Sintaks .text.toString() di atas berfungsi untuk mengambil isi dari sebuah edittext kemudian menyimpannya dalam sebuah variabel.
             * Tambahan .trim() berfungsi untuk menghiraukan spasi jika ada, sehingga nilai yang didapat hanya berupa angka.
             */
            val inputWidth = editTextWidt.text.toString().trim()
            val inputHeight = editTextHeight.text.toString().trim()
            val inputLength = editTextLength.text.toString().trim()

            /**
             * Sintaks .isEmpty() berfungsi untuk mengecek apakah inputan dari edittext itu masih kosong. Jika iya,
             * maka kita akan menampilkan pesan error  dan mengganti variabel boolean isEmptyField menjadi true supaya bisa lanjut ke proses selanjutnya.
             */
            var isEmptyFields = false

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                editTextWidt.error = "Field Ini Tidak Boleh Kosong!"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                editTextHeight.error = "Field Ini Tidak Boleh Kosong!"
            }
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                editTextLength.error = "Field Ini Tidak Boleh Kosong!"
            }

            /**
             * Sintaks !isEmptyFields memiliki arti "jika semua inputan tidak kosong".
             * Jika kondisi tersebut terpenuhi maka langkah selanjutnya yaitu melakukan proses perhitungan, namun di sini kita tidak dapat langsung melakukan perhitungan.
             * Hal ini karena secara default input di edittext tipe datanya berupa String, maka kita perlu untuk merubah tipe datanya terlebih dahulu menjadi double.
             * Setelah selesai proses perhitungan, maka selanjutnya yaitu menampilkan hasil pada textview tvResult.
             * Di sini dapat dilihat bahwa kita perlu merubah datanya yang sebelumnya double menjadi string lagi,
             * hal ini karena untuk menampilkan ke dalam textview harus menggunakan tipe data string.
             */
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                textViewResult.text = volume.toString()
            }

        }
    }
}