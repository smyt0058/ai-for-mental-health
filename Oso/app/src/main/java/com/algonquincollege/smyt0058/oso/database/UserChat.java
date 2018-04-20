package com.algonquincollege.smyt0058.oso.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.algonquincollege.smyt0058.oso.models.ChatMessage;

import java.util.ArrayList;

/**
 * Created by Jason on 2018-04-10.
 */

@Entity
public class UserChat {

    @PrimaryKey
    private int                     userID;

    @ColumnInfo
    private ArrayList<ChatMessage> chatHistory;

    @Ignore
    public UserChat() {
    }

    public UserChat(int userID, ArrayList<ChatMessage> chatHistory) {
        this.userID = userID;
        this.chatHistory = chatHistory;
    }

    public int getId() {
        return userID;
    }

    public void setId(int id) {
        this.userID = id;
    }

    public ArrayList<ChatMessage> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<ChatMessage> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
