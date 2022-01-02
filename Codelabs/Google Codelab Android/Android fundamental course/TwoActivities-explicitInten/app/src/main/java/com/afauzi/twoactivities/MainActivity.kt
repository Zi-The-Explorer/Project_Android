package com.afauzi.twoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION", "UNUSED_LAMBDA_EXPRESSION")
class MainActivity : AppCompatActivity() {

    private lateinit var messageEditText: EditText

    // Deklarasi variable untuk menampung header balasan dan TextViews balasan
    private lateinit var replyHeadTextView: TextView
    private lateinit var replyTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "-------------------------")
        Log.d(LOG_TAG, "onCreate")



        messageEditText = findViewById(R.id.edit_text_main)

        // mendapatkan referensi dari layout ke header balasan dan TextView balasan
        replyHeadTextView = findViewById(R.id.text_header_reply)
        replyTextView = findViewById(R.id.text_message_reply)

    }


    fun launchSecondActivity(view: View) {
          // TODO : Mencetak Log, dan hasil -> "Button Clicked!"
//        Log.d(LOG_TAG, "Button Clicked!")
//        Log.d(LOG_TAG, EXTRA_MESSAGE)


        // TODO : Mendapatkan Text dari Edit Text
        val message: String = messageEditText.text.toString()

        // TODO : Cara menghubungkan antara halaman
        val inten = Intent(this, ActivitySecond::class.java)
        startActivityForResult(inten, TEXT_REQUEST)


            //TODO :  konstanta EXTRA_MESSAGE sebagai kunci dan string-nya sebagai nilai,
            // nilai nya di dapat dari variabel message yg nilai nya string yang di input dari edit text
            inten.putExtra(EXTRA_MESSAGE, message)


        // TODO : Ini Juga Bisa, lebih simple
//        startActivity(Intent(this, ActivitySecond::class.java)
    }

    // Buat metode callback onActivityResult()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                val reply = data?.getStringExtra(ActivitySecond.EXTRA_REPLY)
                replyHeadTextView.setVisibility(View.VISIBLE)
                replyTextView.setText(reply)
                replyTextView.setVisibility(View.VISIBLE)
            }
        }
    }

    // metode callback
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    // metode callback
    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    // metode callback
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    // metode callback
    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    // metode callback
    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    // metode callback
    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    companion object {
        //TODO :  Nilai pada variabel LOG_TAG, untuk menampilkan aksi pada logcat apakah berfungsi atau tidak
        // Konstanta ini menggunakan nama kelas itu sendiri sebagai tag-nya.
        private val LOG_TAG = MainActivity::class.java.simpleName
        const val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"

        // Tambahkan konstanta publik di bagian atas kelas untuk mendefinisikan kunci untuk tipe respons tertentu yang Anda inginkan
        const val TEXT_REQUEST: Int = 1
    }
}