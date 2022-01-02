package com.afauzi.notificationbasic

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {


    private lateinit var mNotifyManager: NotificationManager

    val NOTIFICATION_ID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val mNotifyButton = findViewById<Button>(R.id.notify)
        mNotifyButton.setOnClickListener{
            sendNotification()
            Toast.makeText(this, "button aktif", Toast.LENGTH_LONG).show()
            Log.d("Button", "Aktif")
        }

    }

    fun sendNotification() {
        val notifyBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, NOTIFICATION_SERVICE)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_notify);

        val myNotification: Notification = notifyBuilder.build()
        mNotifyManager.notify(NOTIFICATION_ID, myNotification)

        



    }
}