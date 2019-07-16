package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Shubham Raja on 1/7/2017.
 */

public class SmsServ extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this,BroadServ.class);
        if (remoteMessage.getData().size() > 0) {
            String message = remoteMessage.getData().get("message");
            Bundle bundle = new Bundle();
            bundle.putString("message",message);
            intent.putExtras(bundle);

        }

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi = PendingIntent.getActivities(this, 0 , new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this);

        ncb.setContentTitle("New Notification");
        ncb.setContentText(remoteMessage.getNotification().getBody());
        ncb.setAutoCancel(true);
        ncb.setSmallIcon(R.drawable.ic_scc);
        ncb.setContentIntent(pi);

        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,ncb.build());
    }
}
