package com.algonquincollege.smyt0058.oso.notifications;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.R;

//import com.algonquincollege.smyt0058.oso.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    // hardcoded identifier. purpose unknown
    static final String channelId = "1";
    static final String channelName = "the-notification-channel";
    // purpose unknown
    private final int requestCodeUnknown = 100;
    private static int notificationIndex = 0;

    // method of incrementing
    private int getNotificationId() {
        //requestCodeUnknown = (int)(System.currentTimeMillis() / 1000);
        return ++notificationIndex;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // declarations
        NotificationManager notificationManager;
        NotificationCompat.Builder builder;
        String action;

        // new id for each onReceive
        int id = getNotificationId();

        //
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // ---
        // for android 8 (oreo) a channel is created before
        // there are other ways of notating this conditional directive
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(nc);
        }


        //
        // ---
        //

        action = intent.getAction();
        Log.i("Also", "AlarmReceiver.onReceive is now " + action);


        // ---
        /// this is the target of the the
        Intent intentGoesHere = new Intent(context, NotificationActivity.class);
        intentGoesHere.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendi = PendingIntent.getActivity(
                context,
                id,
                intentGoesHere,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        builder = new NotificationCompat.Builder(context, channelId)
                .setContentIntent(pendi)
                .setSmallIcon(R.drawable.ic_oso_face_icon)
                .setContentTitle("title "+String.valueOf(id))
                .setContentText("notification text")
                .setAutoCancel(true);

        notificationManager.notify(id, builder.build());
        // this does not work.
//        notificationManager.notify(requestCodeUnknown, builder.build());
        Log.i("Also", String.valueOf(id)+" is the notification id");
    }
}