package com.algonquincollege.smyt0058.oso.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.algonquincollege.smyt0058.oso.ChatActivity;

/**
 *
 */
public class NotificationAccepter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NOTIFICATION", "notification accepted");


        // start chat activity with action that will initiate questionnaire

        // check if chat activity is open
        if (ChatActivity.isRunning) {
            // if it is ask it to initiate questionnaire and appear

            //TODO - initiate questionnaire

            Intent intentGoesHere = new Intent(
                    context,
                    ChatActivity.class
            );
            context.startActivity(intentGoesHere);


        } else {


            Intent intentGoesHere = new Intent(
                    context,
                    ChatActivity.class
            );
            context.startActivity(intentGoesHere);
        }

    }
}
