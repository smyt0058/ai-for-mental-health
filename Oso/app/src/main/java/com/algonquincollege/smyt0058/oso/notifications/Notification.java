package com.algonquincollege.smyt0058.oso.notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * see README.md
 */
public class Notification {

    // for notification that appears to user
    static private final int notificationId = 100;
    static final String channelId = "1";
    static final String channelName = "the-notification-channel";

    // time of day for alarm events
    static private int hour;
    static private int minute;

    // purpose unknown, used when creating pending intents
    static private int requestCode = 0;

    /**
     * "init" registers an alarm with the android OS
     * @param context
     */
    static public void init(Context context) {

        Log.i("NOTIFICATION", "init");

        // in case no alarm has ever been set
        SharedPreferences prefs = SharedPrefUtils.getAppState(context);
        long next = prefs.getLong(SharedPrefUtils.NEXT_QUESTIONNAIRE_DATE, 0);
        if (next == 0) {
            next = getNextTime(context);
            SharedPrefUtils.putNextDateState(context, next);
        }

        // because user adjusting hour and day might have put next in past
        // the if statement could be combined with the one above for
        // what is about the same as getNextTime()
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long now = c.getTimeInMillis();
        if (next < now) {
            next += 24 * 60 * 60 * 1000;
            SharedPrefUtils.putNextDateState(context, next);
        }

        // intent - when alarm is activated, broadcast to this receiver
        Intent i = new Intent(
                context,
                AlarmReceiver.class
        );
        // wrap that in a pending intent
        PendingIntent pi = PendingIntent.getBroadcast(
                context,
                requestCode,
                i,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // for the system service that manages alarms
        AlarmManager noteAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        noteAlarmManager.set(
                AlarmManager.RTC,
                next,
                pi
        );

    }

    /**
     * "create" posts a notification to the user
     * @param context
     */
    static public void create(Context context) {

        NotificationManager notificationManager;
        NotificationCompat.Builder builder;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // for android 8 (oreo) a channel is created before
        // there are other ways of notating this conditional directive
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(nc);
        }

        Intent intentGoesHere = new Intent(context, NotificationReceiver.class);
        // note: this action has been changed. should delete next line
        intentGoesHere.setAction("com.algonquincollege.smyt0058.oso.notif");
        PendingIntent pendi = PendingIntent.getBroadcast(
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

    /**
      * "getNextTime" gets the SharedPreferences hour of day, within the next 24 hour block
      * @returns time in milliseconds
      */
    static private long getNextTime(Context context) {

        SharedPreferences prefs = SharedPrefUtils.getAppState(context);
        hour = prefs.getInt(SharedPrefUtils.QUESTIONNAIRE_HOUR_OF_DAY, 13);
        minute = prefs.getInt(SharedPrefUtils.QUESTIONNAIRE_MINUTE_OF_DAY, 0);

        // calendar representation of now
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long now = c.getTimeInMillis();

        // set hour and minute.
        // adjust to tomorrow if that result is not in the future
        // (by five seconds minute at least)
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        if (c.getTimeInMillis() < now + 5 * 1000) {
            c.setTimeInMillis(c.getTimeInMillis() + 24 * 60 * 60 * 1000);
        }

        return c.getTimeInMillis();
    }
}
