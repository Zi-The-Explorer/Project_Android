package com.afauzi.dicodingcodelab_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_explicit_intent.*

class ExplicitIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent)

        toast("Selamat Datang Di Halaman Explicit")

        // Btn move main page
        btn_move_page_main.setOnClickListener {
            movePageMain()
        }

        // btn move implicit page
        btn_move_page_implicit.setOnClickListener {
            movePageImplicit()
        }
    }

    // Berpindah ke Halaman Main
    private fun movePageMain() {
        toast("Berpindah ke halaman Main")
        val intent = Intent(this@ExplicitIntent, MainActivity::class.java)
        startActivity(intent)
    }

    // Berpindah ke Halaman Implicit
    private fun movePageImplicit() {
        toast("Berpindah ke halaman Implicit Intent")
        val intent = Intent(this@ExplicitIntent, ImplicitIntent::class.java)
        startActivity(intent)
    }

    // Toast
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun btnIntentWithData(view: View) {
        val moveWithDataIntent = Intent(this@ExplicitIntent, IntentWithData::class.java)

        // Todo : Perbedaan mendasar antara memindahkan Activity dengan membawa data atau tidak,
        //  adalah dengan menempatkan data ke obyek Intent pada baris ini.
        moveWithDataIntent.putExtra(IntentWithData.EXTRA_NAME, "Akhmad Fauzi") // Todo : .EXTRA_NAME dimana EXTRA_NAME adalah variabel static bertipe data string dan bernilai “extra_name”
        moveWithDataIntent.putExtra(IntentWithData.EXTRA_AGE, 24)
        // Todo : Kita memanfaatkan metode putExtra() untuk mengirimkan data bersamaan dengan obyek Intent.
        //  Sedangkan metode putExtra() itu sendiri merupakan metode yang menampung pasangan key-value dan memiliki beberapa pilihan tipe input seperti berikut:

        startActivity(moveWithDataIntent)
    }
}