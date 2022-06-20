package com.example.finalproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        var airplane = 0

        if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            Toast.makeText(context, "Screen ON", Toast.LENGTH_SHORT).show()
        }
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            Toast.makeText(context, "Screen OFF", Toast.LENGTH_SHORT).show()
        }
        if (intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            Toast.makeText(context, "POWER DISCONNECTED", Toast.LENGTH_SHORT).show()
        }
        if (intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED")) {
            Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show()
        }
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")) {
            Toast.makeText(context, "AIRPLANE MODE CHANGED", Toast.LENGTH_SHORT).show()
        }
    }
}