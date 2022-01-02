package com.afauzi.intentappdicodingcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject: TextView = findViewById(R.id.tv_object_received)

        /**
         * Metode putExtra() yang kita pilih saat ini adalah putExtra(String name, Parcelable value).
         *
         * EXTRA_PERSON merupakan variabel static bertipe data string dan bernilai “extra_person”. Berfungsi sebagai key untuk mendapatkan value data yang dikirim
         *
         * Selanjutnya pada MoveWithObjectActivity kita akan mengambil nilai seperti berikut:
         */
        val person = intent.getParcelableExtra<ModelPerson>(EXTRA_PERSON) as ModelPerson
        /*
         * Karena obyek ini terdiri dari beragam tipe data pada atribut atau propertinya, kita hanya cukup membungkus itu semua ke dalam obyek parcelable.
         * Melalui getIntent().getParcelableExtra(Key), Anda dapat mengambil nilai obyek person yang sebelumnya telah dikirim hanya dengan satu variabel,
         * bayangkan jika kita tidak menggunakan parcelable, maka kita harus mengirim data satu per satu.
         * Jika datanya sedikit mungkin tidak masalah tapi jika datanya puluhan, tentu akan merepotkan kan?
         */


        /**
         *  menampilkan data obyek yang sudah diterima
         */
        val text = """
            Name : ${person.name.toString()}
            Email: ${person.email}
            Age: ${person.age}
            Location: ${person.city}
        """.trimIndent()
        tvObject.text = text
    }
}