package com.afauzi.dicodingcodelab_intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class IntentWithData : AppCompatActivity() {

    // deklarasi text view
    private lateinit var textRead: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_with_data)

        // inisialisasi lateinit untuk memanggil textview pada layout
        textRead = findViewById(R.id.text_view_read_data)

        // Todo : Get Data Setelah data dikirimkan, selanjutnya adalah mengambil data tersebut
        // Todo : mengambil nilai data berdasarkan key yang dikirimkan dengan menggunakan metode getIntent().getStringExtra(key).
        val name = intent.getStringExtra(EXTRA_NAME)
        val age = intent.getIntExtra(EXTRA_AGE, 0)

        val text = """
            My Name : $name
            MyAge   : $age
        """.trimIndent()
        textRead.text = text

    }


     // TODO :  Konstanta yaitu sebuah variabel yang nilainya tetap,
    //  Todo : biasanya digunakan sebagai key(kunci) yang dipakai untuk mengirim dan menerima data
    // todo : supaya variabel ini bisa diakses dari kelas lain, anda harus memasukannya ke dalam companion object.
    // Todo : saat di MainActivity kita bisa memanggil variabel tersebut dengan cara menulis kelasnya dulu,
    //  yaitu seperti ini IntentWithData.EXTRA_NAME.

    companion object {
        //
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
    }
}