package com.polije.dermoally_apps.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.polije.dermoally_apps.ui.view.LoginActivity

class SessionExpiredReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.polije.dermoally_apps.SESSION_EXPIRED") {
            val loginIntent = Intent(context, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context?.startActivity(loginIntent)
        }
    }
}
