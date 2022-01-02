package com.afauzi.hellocompact

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*


class MainActivity : AppCompatActivity() {

    // menampung objek TextView untuk textview Hello World
    private lateinit var mHelloTextView: TextView

    // menambahkan warna, untuk penamaan warna harus sama dengan yang ada di color.xml
    private var mColorArray: Array<String> = arrayOf(
        "red",
        "pink",
        "purple",
        "deep_purple",
        "indigo",
        "blue",
        "light_blue",
        "cyan",
        "teal",
        "green",
        "light_green",
        "lime",
        "yellow",
        "amber",
        "orange",
        "deep_orange",
        "brown",
        "grey",
        "blue_grey",
        "black"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi findViewByID() untuk mendapatkan referensi tentang contoh TextView dan menetapkannya ke variabel privat tersebut:
        mHelloTextView = findViewById(R.id.hello_textView)

        // Restore Saved Instance state (the text color)
        if (savedInstanceState != null)  {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save the current text color
        outState.putInt("color", mHelloTextView.currentTextColor)
    }


    // Implementasi Prilaku Tombol saat di klik untuk mengubah warna color text secara acak, dari warna yang telah di definisikan
    fun changeColor(view: View) {

        // Buat Object secara acak, yang diambil dari class java untuk menghasilkan nomor acak sederhana
        val random: Random = Random()

        // instance acak untuk memilih warna secara acak dari deklarasi mColorArray di atas
        val colorName: String = mColorArray[random.nextInt(20)]
        // Metode nextInt() dengan argumen 20 akan mendapatkan integer acak antara nomor 0 sampai 19.
        // integer itu sebagai indeks larik untuk mendapatkan nama warna.

        // Dapatkan Identifier (Integer) untuk nama warna dari resource sumber daya value color.xml
        val colorResourceName: Int = resources.getIdentifier(colorName, "color", applicationContext.packageName)
        // Metode getResources() mendapatkan semua sumber daya untuk aplikasi Anda.
        // Metode getIdentifier() mencari nama warna (string) dalam sumber daya warna ("color") untuk nama paket saat ini.

        // Dapatkan ID Integer dari sumber daya dan tetapkan ke variabel colorRes
        val colorRes: Int = ContextCompat.getColor(this, colorResourceName)

        // Setel warna tampilan teks Hello World tke ID sumber daya warna:
        mHelloTextView.setTextColor(colorRes)


    }
}