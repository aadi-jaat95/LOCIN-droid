package com.locin.app.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            // TODO: Restart FocusMonitorService if there was an active session
            // TODO: Re-schedule any pending reminders using WorkManager
        }
    }
}
