package com.afauzi.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val intentAction: String? = intent.action

        val toastMessage: String? = null
        when (intentAction) {
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
                Log.d("Power", "Connected")
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
                Log.d("Power", "Disconnected")
            }
        }





    }
}