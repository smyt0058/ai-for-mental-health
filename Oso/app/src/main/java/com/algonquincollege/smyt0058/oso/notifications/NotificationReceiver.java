package com.algonquincollege.smyt0058.oso.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.ChatActivity;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NOTIFICATION", "on receive the notification press");

        // start chat activity with action that will initiate questionnaire

        // check if chat activity is open
         if (ChatActivity.isRunning) {
             ChatActivity.staticGo();
         }

        // if it is ask it to initiate questionnaire and appear

        //Log.i("NOTIFICATION", "chat is running");

        Intent intentGoesHere = new Intent(
                context,
                ChatActivity.class
        );
        intentGoesHere.setAction("ca.edumedia.INITIATE");
        context.startActivity(intentGoesHere);

        // gets the next time
        Notification.init(context);
    }
}
