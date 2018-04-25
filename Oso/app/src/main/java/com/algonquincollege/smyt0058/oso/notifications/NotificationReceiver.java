package com.algonquincollege.smyt0058.oso.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.ChatActivity;

/**
 * see README.md
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NOTIFICATION", "on receive the notification press");

        // check if chat activity is open
         if (ChatActivity.isRunning) {
             // request questionnaire from survey because
             // the intent action below would not be run in OnCreate
             ChatActivity.staticGo();
         }

        Intent intentGoesHere = new Intent(
                context,
                ChatActivity.class
        );
        intentGoesHere.setAction("ca.edumedia.INITIATE");
        context.startActivity(intentGoesHere);

        // register the next time for a notification to appear
        Notification.init(context);
    }
}
