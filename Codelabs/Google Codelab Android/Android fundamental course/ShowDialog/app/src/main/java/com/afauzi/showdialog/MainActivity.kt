package com.afauzi.showdialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickShowAlert(view: View) {
        // dialog baru hanya saat tombol diklik, yang berguna jika dialog jarang dilihat (saat pengguna mengambil jalur tertentu di aplikasi).
        // Akan tetapi, jika dialog sering mungkin, sebaiknya Anda membangun dialog sekali di metode onCreate() ,
        // lalu menjalankan dialog di metode onClickShowAlert .
        val myAlertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set judul dialog
        myAlertBuilder.setTitle("Hello Cuy")

        // Set dialog pesan
        myAlertBuilder.setMessage("Klik Ok kalo mau lanjut, Cancel kalo gajadi")

        // tambahkan tombol didalam alert
        // Tombol OK
        myAlertBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // user clicked OK Button
            Log.d("Dialog: ", "Get OK")
            Toast.makeText(applicationContext, "OK Clicked!", Toast.LENGTH_LONG).show()
        })

        // Tombol Cancel
        myAlertBuilder.setNegativeButton("Cancelled!", DialogInterface.OnClickListener { dialog, which ->
            // User Clicked Cancel Button
            Log.d("Dialog", "Get Cancel")
            Toast.makeText(applicationContext, "Cancel Clicked", Toast.LENGTH_LONG).show()
        })

        // Tambahkan show() yang membuat lalu menampilkan dialog peringatan, ke akhir onClickShowAlert() :

        // Create and show the alert dialog
        myAlertBuilder.show()

    }
}