package com.example.activitytest.six.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       val data = intent.getStringExtra("data")
        val abort = intent.getBooleanExtra("abort", false)
        Toast.makeText(context, "received in MyBroadcastReceiver ${data}",
            Toast.LENGTH_SHORT).show()
        if (abort) {
            abortBroadcast()
        }
    }

}