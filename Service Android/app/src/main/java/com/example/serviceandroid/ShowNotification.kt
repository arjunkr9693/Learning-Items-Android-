package com.example.serviceandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ShowNotification : Service() {

    private val channel_id = "1";
    private val channel_name = "Normal Notification"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyApp", "OnStart is Called")
        Toast.makeText(this, "onStart called", Toast.LENGTH_SHORT).show()
        showNotification()
        return START_STICKY
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Running Service")
            .setContentText("Notification is getting displayed because a service is running")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification)
    }

    override fun onCreate() {
        Log.d("MyApp", "OnCreate is Called")
        Toast.makeText(this, "onCreate Called", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d("MyApp", "OnDestroy is Called")
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show()
        removeNotification()
        super.onDestroy()
    }

    private fun removeNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(1)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}