 package com.afauzi.dialogperingatanalert

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
         val myAlertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

         // set the dialog title
         myAlertBuilder.setTitle(getString(R.string.alert_title))
         // set the dialog message
         myAlertBuilder.setMessage(getString(R.string.alert_message))


         // add OK the button
         myAlertBuilder.setPositiveButton("OK", DialogInterface.OnClickListener() { dialog, which ->
             // user click OK button
             Toast.makeText(applicationContext, "Pressed OK", Toast.LENGTH_SHORT).show();
             Log.d("Alert", "Press OK \nmaaf Toast Rusak!")
         })

         // add Cancel Button
         myAlertBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
             // user click cancel button
             Toast.makeText(applicationContext, "Pressed Cancel", Toast.LENGTH_SHORT).show();
             Log.d("Alert", "Press Cancel \nmaaf Toast Rusak!")
         })

         // Create and show the alert
         myAlertBuilder.show()


     }
 }