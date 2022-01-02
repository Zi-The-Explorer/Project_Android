package com.afauzi.dicodingcodelab_intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ImplicitIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)

        toast("Selamat datang di halaman implicit")
    }

    companion object {
        // Request Code Untuk Gallery
        const val SELECT_PICTURE = 1
    }

    // Toast
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun btn_page_implicit(view: View) {
        // get photo from gallery
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.setType("image/*")
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_PICTURE)
    }

    fun btnDialNumber(view: View) {
        val phoneNumber = "0821129666360"
        val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")) // Todo : Dimana “tel” adalah sebuah skema yang disepakati untuk sumber daya telepon
        startActivity(dialPhoneIntent)

    }
}