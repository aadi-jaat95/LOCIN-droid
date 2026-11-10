package com.locin.app.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.locin.app.LocinApplication
import com.locin.app.R

class FocusMonitorService : Service() {

    private var isRunning = false
    private var elapsedSeconds = 0
    private val NOTIFICATION_ID = 1001

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startFocusSession()
            ACTION_STOP -> stopFocusSession()
            ACTION_PAUSE -> pauseFocusSession()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startFocusSession() {
        isRunning = true
        // TODO: Start timer, monitor apps, send reminders
    }

    private fun stopFocusSession() {
        isRunning = false
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun pauseFocusSession() {
        isRunning = false
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, LocinApplication.CHANNEL_FOCUS_SERVICE)
            .setContentTitle("Focus Session Active")
            .setContentText("Stay focused! ${formatTime(elapsedSeconds)} elapsed")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun formatTime(seconds: Int): String {
        val mins = seconds / 60
        val secs = seconds % 60
        return "%02d:%02d".format(mins, secs)
    }

    companion object {
        const val ACTION_START = "com.locin.app.ACTION_START"
        const val ACTION_STOP = "com.locin.app.ACTION_STOP"
        const val ACTION_PAUSE = "com.locin.app.ACTION_PAUSE"
    }
}
