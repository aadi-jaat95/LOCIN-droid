package com.locin.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.firebase.FirebaseApp

class LocinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Create notification channels
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Focus Service Channel
            val focusChannel = NotificationChannel(
                CHANNEL_FOCUS_SERVICE,
                "Focus Service",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows when focus session is running"
                setShowBadge(false)
            }

            // Reminders Channel
            val reminderChannel = NotificationChannel(
                CHANNEL_REMINDERS,
                "Focus Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Focus reminders during study sessions"
                enableVibration(true)
            }

            // Schedule Channel
            val scheduleChannel = NotificationChannel(
                CHANNEL_SCHEDULE,
                "Schedule Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Scheduled study session reminders"
            }

            // Blocking Alerts Channel
            val blockingChannel = NotificationChannel(
                CHANNEL_BLOCKING,
                "Blocking Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Alerts when apps or sites are blocked"
            }

            notificationManager.createNotificationChannels(
                listOf(focusChannel, reminderChannel, scheduleChannel, blockingChannel)
            )
        }
    }

    companion object {
        const val CHANNEL_FOCUS_SERVICE = "focus_service_channel"
        const val CHANNEL_REMINDERS = "reminders_channel"
        const val CHANNEL_SCHEDULE = "schedule_channel"
        const val CHANNEL_BLOCKING = "blocking_channel"
    }
}
