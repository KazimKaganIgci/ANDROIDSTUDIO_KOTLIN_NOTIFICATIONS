package com.kazim.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val CHANNEL_ID ="channelID"
    val CHANNEL_NAME ="channelName"
    val NOTIFICATIONS_ID =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val intent =Intent(this,MainActivity::class.java)
        val pendingIntent =TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent).getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT)
        }

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("NOTIFICATION")
            .setContentText("CONTENT TEXT")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)



        val notificationManager =NotificationManagerCompat.from(this)

        btn1.setOnClickListener{
            notificationManager.notify(NOTIFICATIONS_ID,builder.build())
        }

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor= Color.GREEN
                enableLights(true)

            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}