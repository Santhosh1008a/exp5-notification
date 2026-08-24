package com.example.exp5notification.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.exp5notification.MainActivity
import com.example.exp5notification.R

class NotificationHelper(private val context: Context) {

    companion object {
        const val CHANNEL_LOGIN = "login_channel"
        const val CHANNEL_REMINDERS = "reminders_channel"
        const val CHANNEL_PROGRESS = "progress_channel"
        
        const val NOTIF_LOGIN_ID = 101
        const val NOTIF_REMINDER_ID = 102
        const val NOTIF_PROGRESS_ID = 103
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val loginChannel = NotificationChannel(
                CHANNEL_LOGIN,
                "Login Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for user login events"
            }

            val reminderChannel = NotificationChannel(
                CHANNEL_REMINDERS,
                "Reading Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminders to continue reading your favorite books"
            }

            val progressChannel = NotificationChannel(
                CHANNEL_PROGRESS,
                "Reading Progress",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Updates on your reading progress"
            }

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(loginChannel)
            manager.createNotificationChannel(reminderChannel)
            manager.createNotificationChannel(progressChannel)
        }
    }

    fun showLoginNotification(name: String, usn: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_LOGIN)
            .setSmallIcon(R.drawable.ic_book) // We'll create this icon
            .setContentTitle("BookNest")
            .setContentText("$name logged in successfully!")
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "$name logged in successfully!\nUSN: $usn\nWelcome back to your library."
            ))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        sendNotification(NOTIF_LOGIN_ID, builder)
    }

    fun showContinueReadingNotification(bookTitle: String, chapter: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_PROGRESS)
            .setSmallIcon(R.drawable.ic_book)
            .setContentTitle("BookNest — Continue Reading")
            .setContentText("Continue reading \"$bookTitle\"")
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "Continue reading \"$bookTitle\"\nYou left off at $chapter.\nPick up where you left off."
            ))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        sendNotification(NOTIF_PROGRESS_ID, builder)
    }

    fun showReadingReminderNotification(bookTitle: String, progress: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 2, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_REMINDERS)
            .setSmallIcon(R.drawable.ic_book)
            .setContentTitle("BookNest — Reading Reminder")
            .setContentText("Time to continue your book!")
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "Time to continue your book!\n$bookTitle — $progress% completed\nContinue where you left off."
            ))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        sendNotification(NOTIF_REMINDER_ID, builder)
    }

    private fun sendNotification(id: Int, builder: NotificationCompat.Builder) {
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(id, builder.build())
            }
        }
    }
}
