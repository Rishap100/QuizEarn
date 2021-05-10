package com.quizearn.rishap.quiz20;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by RISHAP on 14-05-2020.
 */

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.logo_quizearn2)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setContentText(message);

        NotificationManagerCompat manger = NotificationManagerCompat.from(this);
        manger.notify(999, builder.build());
    }
}
