package com.algonquincollege.smyt0058.oso;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.algonquincollege.smyt0058.oso.models.ChatMessage;

import java.util.ArrayList;

/**
 * Created by Jason on 2018-03-24.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private ArrayList<ChatMessage> messagesList;

    private Context mContext;

    public ChatAdapter(ArrayList<ChatMessage> messagesList){
        this.messagesList = messagesList;
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, int position) {
        ChatMessage message = this.messagesList.get(position);

        if(message.MSG_TYPE_RECEIVED.equals(message.getMsgType()))
        {
            // Show received message in left linearlayout.
            holder.receivedMsgLayout.setVisibility(ConstraintLayout.VISIBLE);
            holder.receivedMsgTextView.setText(message.getMessageContent());
            holder.receivedMsgTime.setText(message.getTimeString());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            holder.sentMsgLayout.setVisibility(ConstraintLayout.GONE);
        }

        // If the message is a sent message.
        else if(message.MSG_TYPE_SENT.equals(message.getMsgType()))
        {
            // Show sent message in right linearlayout.
            holder.sentMsgLayout.setVisibility(ConstraintLayout.VISIBLE);
            holder.sentMsgTextView.setText(message.getMessageContent());
            holder.sentMsgTime.setText(message.getTimeString());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            holder.receivedMsgLayout.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.oso_chat_layout, parent, false);
        return new ChatViewHolder(view);
    }

    public void addMessage(ChatMessage theMessage) {
        messagesList.add(theMessage);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(messagesList==null)
        {
            messagesList = new ArrayList<ChatMessage>();
        }
        return messagesList.size();
    }





}
