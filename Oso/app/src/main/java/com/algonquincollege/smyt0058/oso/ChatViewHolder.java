package com.algonquincollege.smyt0058.oso;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jason on 2018-03-24.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder {

    ConstraintLayout receivedMsgLayout;

    ConstraintLayout sentMsgLayout;

    TextView        receivedMsgTextView;

    TextView        sentMsgTextView;

    TextView        receivedMsgTime;

    TextView        sentMsgTime;

    public ChatViewHolder(View itemView) {
        super(itemView);

        if(itemView!=null) {
            receivedMsgLayout = (ConstraintLayout) itemView.findViewById(R.id.chat_received_layout);
            sentMsgLayout = (ConstraintLayout) itemView.findViewById(R.id.chat_sent_layout);
            receivedMsgTextView = (TextView) itemView.findViewById(R.id.chat_message_received);
            sentMsgTextView = (TextView) itemView.findViewById(R.id.chat_message_sent);
            receivedMsgTime = (TextView) itemView.findViewById(R.id.chat_message_received_time);
            sentMsgTime = (TextView) itemView.findViewById(R.id.chat_message_sent_time);

        }
    }

}
