package com.algonquincollege.smyt0058.oso.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.ChatActivity;

/**
 *
 */
public class AlarmReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NOTIFICATION", "alarm receiver");


        // check if chat activity is open
//        if (ChatActivity.isRunning) {
            // if it is ask it to initiate questionnaire and appear
            //TODO

        Notification.create(context);
    }
}
