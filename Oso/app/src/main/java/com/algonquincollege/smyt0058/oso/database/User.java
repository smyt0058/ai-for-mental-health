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
public class User {

    @PrimaryKey(autoGenerate = true)
    private int                     id;

    @ColumnInfo
    private ArrayList<ChatMessage> chatHistory;

    @Ignore
    public User() {
    }

    public User(int id, ArrayList<ChatMessage> chatHistory) {
        this.id = id;
        this.chatHistory = chatHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ChatMessage> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<ChatMessage> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
