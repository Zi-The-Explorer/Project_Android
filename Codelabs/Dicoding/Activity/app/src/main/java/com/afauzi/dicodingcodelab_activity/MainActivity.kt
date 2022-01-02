package com.afauzi.dicodingcodelab_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Deklarasi untuk aksi edit text
    private lateinit var editTextPanjang: EditText
    private lateinit var editTextLebar: EditText
    private lateinit var editTextTinggi: EditText

    // Deklarasi untuk aksi Button hitung
    private lateinit var buttonHasil: Button

    // Deklarasi untuk aksi text menampilkan hasil input edit text
    private lateinit var txtViewHasil: TextView

    // Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
    // Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas.


    companion object {
        private const val STATE_RESULT = "state_result"
    }

    // Metode onCreate() merupakan metode utama pada activity. Di sinilah kita dapat mengatur layout xml.
    // Semua proses inisialisasi komponen yang digunakan akan dijalankan di sini.
    // Pada metode ini kita casting semua komponen view yang kita telah deklarasikan sebelumnya, agar dapat kita manipulasi.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //Maksud baris di atas adalah kelas MainActivity akan menampilkan tampilan yang berasal dari layout activity_main.xml.

        // Initialized variabel Hasil deklarasi
        editTextPanjang = editText_panjang
        editTextLebar = editText_lebar
        editTextTinggi = editText_tinggi

        // initial variabel button
        buttonHasil = btn_hitung

        // initial text view
        txtViewHasil = txt_view_hasil



        // Kita memasang event click listener untuk obyek buttonHasil sehingga sebuah aksi dapat dijalankan ketika obyek tersebut diklik.
        buttonHasil.setOnClickListener {
            hitungHasil()
        }

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            txtViewHasil.text = result
        }

    }

    // Perhatikan metode onSaveInstanceState.
    // Di dalam metode tersebut, hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya.
    // Untuk menyimpan data disini menggunakan konsep Key-Value, dengan STATE_RESULT sebagai key dan isi dari tvResult sebagai value.
    // Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur.
    // Di sini kita perlu menambahkan onSaveInstanceState karena ketika orientasi berubah, Activity tersebut akan di-destroy dan memanggil fungsi onCreate lagi,
    // sehingga kita perlu menyimpan nilai hasil perhitungan tersebut supaya data tetap terjaga dan tidak hilang ketika orientasi berubah.

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, txtViewHasil.text.toString())
    }

    private fun hitungHasil() {

        // Sintaks .text.toString() di atas berfungsi untuk mengambil isi dari sebuah EditText kemudian menyimpannya dalam sebuah variabel.
        // Tambahan .trim() berfungsi untuk menghiraukan spasi jika ada, sehingga nilai yang didapat hanya berupa angka.
        val panjang = editTextPanjang.text.toString().trim()
        val lebar = editTextLebar.text.toString().trim()
        val tinggi = editTextTinggi.text.toString().trim()

        var isEmpetyField = false

        // Sintaks .isEmpty() berfungsi untuk mengecek apakah inputan dari Editext itu masih kosong.
        // Jika iya, maka kita akan menampilkan pesan error dengan menggunakan .setError("Field ini tidak boleh kosong") dan mengganti variabel Boolean isEmptyField menjadi true supaya bisa lanjut ke proses selanjutnya.
        if (panjang.isEmpty()) {
            isEmpetyField = true
            editTextPanjang.error = "Field ini tidak boleh kosong"
        }
        if (lebar.isEmpty()) {
            isEmpetyField = true
            editTextLebar.error = "Field ini tidak boleh kosong"
        }
        if (tinggi.isEmpty()) {
            isEmpetyField = true
            editTextTinggi.error = "Field ini tidak boleh kosong"
        }

        // Sintaks !isEmptyFields memiliki arti "jika semua inputan tidak kosong".
        // Jika kondisi tersebut terpenuhi, maka langkah selanjutnya yaitu melakukan proses perhitungan.
        if (!isEmpetyField) {
            val volume = panjang.toDouble() * lebar.toDouble() * tinggi.toDouble()
            txtViewHasil.text = volume.toString()

            // Karena yang didapat dari EditText berupa String maka Anda perlu mengkonversinya terlebih dahulu dengan cara toDouble().
            // Langkah terakhir yaitu menampikan hasil perhitungan pada TextView tvResult dengan menggunakan .text
            // Di sini dapat kita lihat bahwa kita perlu merubah datanya yang sebelumnya Double menjadi String dengan menggunakan toString() karena untuk menampilkan data dengan .text harus berupa String.
        }
    }
}