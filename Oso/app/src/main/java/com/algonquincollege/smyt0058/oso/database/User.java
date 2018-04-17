package com.algonquincollege.smyt0058.oso.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
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
//
//    @ColumnInfo(name = "paw_points")
//    private int                     pawPoints;
//
//    @ColumnInfo(name = "is_wearing_shirt")
//    private boolean                 isWearingShirt;
//    @ColumnInfo(name = "is_wearing_hat")
//    private boolean                 isWearingHat;
//    @ColumnInfo(name = "is_wearing_headbow")
//    private boolean                 isWearingHeadbow;
//    @ColumnInfo(name = "is_wearing_monocle")
//    private boolean                 isWearingMonocle;
//    @ColumnInfo(name = "is_wearing_bowtie")
//    private boolean                 isWearingBowtie;
//    @ColumnInfo(name = "is_wearing_pink_bowtie")
//    private boolean                 isWearingPinkBowtie;
//    @ColumnInfo(name = "is_wearing_watch")
//    private boolean                 isWearingWatch;
//    @ColumnInfo(name = "is_wearing_mp3")
//    private boolean                 isWearingMp3;
//
//    @ColumnInfo(name = "is_headbow_purchased")
//    private boolean                 isHeadbowPurchased;
//    @ColumnInfo(name = "is_pink_bowtie_purchased")
//    private boolean                 isPinkBowtiePurchased;
//    @ColumnInfo(name = "is_mp3_purchased")
//    private boolean                 isMp3Purchased;
//
//    @ColumnInfo(name = "chosen_app_color_theme")
//    private String                  chosenAppColorTheme;
//
//    @ColumnInfo(name = "is_notification_on")
//    private boolean                 isNotificationOn;

    @ColumnInfo
    private ArrayList<ChatMessage> chatHistory;


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
