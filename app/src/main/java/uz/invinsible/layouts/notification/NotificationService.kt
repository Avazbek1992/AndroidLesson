package uz.invinsible.layouts.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import uz.invinsible.layouts.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class NotificationService : FirebaseMessagingService() {

    private val channelId = "channelId"
    private val channelName = "uz.invinsible.layouts.notification"
    override fun onMessageReceived(message: RemoteMessage) {
        println("onMessageReceived: $message")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotification(message.notification?.title, message.notification?.body)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotification(title: String?, body: String?) {
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val pendingIntent: PendingIntent =
            PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val remoteViews = RemoteViews(applicationContext.packageName, R.layout.item_notification)
        remoteViews.setTextViewText(R.id.not_title, title)
        remoteViews.setTextViewText(R.id.not_subtitle, body)
        remoteViews.setImageViewResource(
            R.id.notification_icon,
            R.drawable.ic_ronaldo
        )

        var builder: Notification.Builder =
            Notification.Builder(applicationContext, "channelId")
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setSmallIcon(R.drawable.ic_ronaldo)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setContentIntent(pendingIntent)

        builder = builder.setContent(remoteViews)
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager?

        notificationManager?.notify(0, builder.build())
    }

}