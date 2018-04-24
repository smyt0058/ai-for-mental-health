package com.algonquincollege.smyt0058.oso.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.algonquincollege.smyt0058.oso.ChatActivity;
import com.algonquincollege.smyt0058.oso.R;

import java.util.Calendar;
import java.util.Date;

/**
 * this is a static class to handle various intent functions
 *
 */
public class Notification {
    // purpose unknown, used when creating pending intents
    static private final int requestCode = 0;

    // for notification that appears to user
    static private final int notificationId = 100;
    static final String channelId = "1";
    static final String channelName = "the-notification-channel";

    // time of day for alarm events
    static private final int hour = 12;
    static private final int minute = 0;

    /**
     *
     * @param context
     */
    static public void init(Context context) {

        long next = getNextTime();

        // intent - when timer goes off broadcast to this receiver
        Intent noteIntent = new Intent(
                context,
                AlarmReceiver.class
        );
        // wrap that in a pending intent
        PendingIntent notePendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                noteIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // for the system service that manages alarms
        AlarmManager noteAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        noteAlarmManager.set(
                AlarmManager.RTC,
                next,
                notePendingIntent
        );

    }

    /**
     *
     * @param context
     */
    static public void createNotification(Context context) {
        NotificationManager notificationManager;
        NotificationCompat.Builder builder;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // for android 8 (oreo) a channel is created before
        // there are other ways of notating this conditional directive
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(nc);
        }

        /// this is the target of the notification the user will press on
        Intent intentGoesHere = new Intent(context, ChatActivity.class);
        intentGoesHere.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendi = PendingIntent.getActivity(
                context,
                requestCode,
                intentGoesHere,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // this is what the notification will look like and say
        builder = new NotificationCompat.Builder(context, channelId)
                .setContentIntent(pendi)
                .setSmallIcon(R.drawable.ic_oso_paw)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setAutoCancel(true);

        notificationManager.notify(notificationId, builder.build());
    }

    static private long getNextTime() {
        // calendar representation of now
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.getTimeInMillis() + 60 * 1000;
    }
    static private long getNextTime_daily() {

        // calendar representation of now
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long now = c.getTimeInMillis();

        // set hour and minute.
        // adjust to tomorrow if that result is not in the future
        // (by a minute at least)
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        if (c.getTimeInMillis() < now + 60 * 1000) {
            c.setTimeInMillis(c.getTimeInMillis() + 24 * 60 * 60 * 1000);
        }

        return c.getTimeInMillis();
    }
}
