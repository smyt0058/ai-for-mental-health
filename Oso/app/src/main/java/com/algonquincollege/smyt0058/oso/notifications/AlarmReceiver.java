package com.algonquincollege.smyt0058.oso.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
*  see README.md
 */
public class AlarmReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NOTIFICATION", "alarm receiver");

        SharedPreferences prefs = SharedPrefUtils.getAppState(context);
        long alarm = prefs.getLong(SharedPrefUtils.NEXT_QUESTIONNAIRE_DATE, 0);
        alarm += 14 * 24 * 60 * 60 * 1000;
        // for testing use one minute: alarm += 60 * 1000;
        SharedPrefUtils.putNextDateState(context, alarm);

        Notification.create(context);


        // this will set the next alarm
        Notification.init(context);
    }
}
