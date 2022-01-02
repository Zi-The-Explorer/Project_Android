package com.afauzi.twoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitySecond : AppCompatActivity() {

    // Untuk menampung object edit text
    private lateinit var editTextReply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent: Intent = getIntent()

        // TODO : Dapatkan string berisi pesan dari ekstra intent menggunakan variabel statis MainActivity.EXTRA_MESSAGE
        val message: String? = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        // TODO : Gunakan findViewByID untuk mendapatkan referensi ke TextView untuk pesan dari layout
        val textView: TextView = findViewById(R.id.text_message)
        // TODO : Setel teks TextView tersebut ke string dari ekstra intent
        textView.setText(message)

        // untuk mendapatkan referensi tentang contoh EditText dan menetapkannya ke variabel privat tersebut
        editTextReply = findViewById(R.id.edit_text_second)



//        Log.d(LOG_TAG, message.toString())
    }

    fun returnReplyMessage(view: View) {
        // mendapatkan teks edit text sebagai string
        val replyMessage: String = editTextReply.text.toString()

        // membuat intent baru untuk respon tersebut
        val replyInten = Intent(this, MainActivity::class.java)

        // Tambahkan string balasan dari EditText ke intent baru sebagai ekstra intent.
        // Karena ekstra adalah pasangan kunci/nilai, di sini kuncinya adalah EXTRA_REPLY dan nilainya adalah balasan
        replyInten.putExtra(EXTRA_REPLY, replyMessage)

        // Setel hasilnya ke RESULT_OK untuk menunjukkan bahwa responsnya berhasil.
        // Kode hasil (termasuk RESULT_OK dan RESULT_CANCELLED) didefinisikan oleh kelas Aktivitas
        setResult(RESULT_OK, replyInten)

        Log.d(LOG_TAG, "End Second Activity")
        // Panggil finish() untuk menutup aktivitas dan kembali ke aktivitas utama
        finish()
    }

    companion object {
        // untuk mencetak Log pada second Activity
        private val LOG_TAG = ActivitySecond::class.java.simpleName
        // kunci untuk extra intent
        const val EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"
    }

}