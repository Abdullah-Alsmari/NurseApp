package com.abdullah.nurseapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessge: RemoteMessage) {
        super.onMessageReceived(remoteMessge)
        Log.e("onMessageReceived: ", remoteMessge.data.toString())

        val title = remoteMessge.notification!!.title
        val content = remoteMessge.notification!!.body
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            checkNotificationChannel("1")
        }

        val notification = NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
//                .setStyle(NotificationCompat.MessagingStyle(person)
//                        .setGroupConversation(false)
//                        .addMessage(title,
//                                currentTimeMillis(), person)
//                )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSound)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification.build())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkNotificationChannel(CHANNEL_ID: String) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = "CHANNEL_DESCRIPTION"
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun onNewToken(p0: String) {
       /* token = p0
        Log.i("token",p0)*/
        super.onNewToken(p0)
    }

}