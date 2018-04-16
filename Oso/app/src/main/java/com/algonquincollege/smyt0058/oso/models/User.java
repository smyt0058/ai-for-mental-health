package com.algonquincollege.smyt0058.oso.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Jason on 2018-04-10.
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int                     id;

    @ColumnInfo(name = "paw_points")
    private int                     pawPoints;

    @ColumnInfo(name = "is_wearing_shirt")
    private boolean                 isWearingShirt;
    @ColumnInfo(name = "is_wearing_hat")
    private boolean                 isWearingHat;
    @ColumnInfo(name = "is_wearing_headbow")
    private boolean                 isWearingHeadbow;
    @ColumnInfo(name = "is_wearing_monocle")
    private boolean                 isWearingMonocle;
    @ColumnInfo(name = "is_wearing_bowtie")
    private boolean                 isWearingBowtie;
    @ColumnInfo(name = "is_wearing_pink_bowtie")
    private boolean                 isWearingPinkBowtie;
    @ColumnInfo(name = "is_wearing_watch")
    private boolean                 isWearingWatch;
    @ColumnInfo(name = "is_wearing_mp3")
    private boolean                 isWearingMp3;

    @ColumnInfo(name = "is_headbow_purchased")
    private boolean                 isHeadbowPurchased;
    @ColumnInfo(name = "is_pink_bowtie_purchased")
    private boolean                 isPinkBowtiePurchased;
    @ColumnInfo(name = "is_mp3_purchased")
    private boolean                 isMp3Purchased;

    @ColumnInfo(name = "chosen_app_color_theme")
    private String                  chosenAppColorTheme;

    @ColumnInfo(name = "is_notification_on")
    private boolean                 isNotificationOn;

//    @ColumnInfo
//    private ArrayList<ChatMessage>  chatHistory;


    public User() {
    }

    public User(int pawPoints,
                boolean isWearingShirt,
                boolean isWearingHat,
                boolean isWearingHeadbow,
                boolean isWearingMonocole,
                boolean isWearingBowtie,
                boolean isWearingPinkBowtie,
                boolean isWearingWatch,
                boolean isWearingMp3,
                boolean isHeadbowPurchased,
                boolean isPinkBowtiePurchased,
                boolean isMp3Purchased,
                String chosenAppColorTheme,
                boolean isNotificationOn) {
        this.id = id;
        this.pawPoints = pawPoints;
        this.isWearingShirt = isWearingShirt;
        this.isWearingHat = isWearingHat;
        this.isWearingHeadbow = isWearingHeadbow;
        this.isWearingMonocle = isWearingMonocole;
        this.isWearingBowtie = isWearingBowtie;
        this.isWearingPinkBowtie = isWearingPinkBowtie;
        this.isWearingWatch = isWearingWatch;
        this.isWearingMp3 = isWearingMp3;
        this.isHeadbowPurchased = isHeadbowPurchased;
        this.isPinkBowtiePurchased = isPinkBowtiePurchased;
        this.isMp3Purchased = isMp3Purchased;
        this.chosenAppColorTheme = chosenAppColorTheme;
        this.isNotificationOn = isNotificationOn;
        //this.chatHistory = chatHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPawPoints() {
        return pawPoints;
    }

    public void setPawPoints(int pawPoints) {
        this.pawPoints = pawPoints;
    }

    public boolean isWearingShirt() {
        return isWearingShirt;
    }

    public void setWearingShirt(boolean wearingShirt) {
        isWearingShirt = wearingShirt;
    }

    public boolean isWearingHat() {
        return isWearingHat;
    }

    public void setWearingHat(boolean wearingHat) {
        isWearingHat = wearingHat;
    }

    public boolean isWearingHeadbow() {
        return isWearingHeadbow;
    }

    public void setWearingHeadbow(boolean wearingHeadbow) {
        isWearingHeadbow = wearingHeadbow;
    }

    public boolean isWearingMonocle() {
        return isWearingMonocle;
    }

    public void setWearingMonocle(boolean wearingMonocle) {
        isWearingMonocle = wearingMonocle;
    }

    public boolean isWearingBowtie() {
        return isWearingBowtie;
    }

    public void setWearingBowtie(boolean wearingBowtie) {
        isWearingBowtie = wearingBowtie;
    }

    public boolean isWearingPinkBowtie() {
        return isWearingPinkBowtie;
    }

    public void setWearingPinkBowtie(boolean wearingPinkBowtie) {
        isWearingPinkBowtie = wearingPinkBowtie;
    }

    public boolean isWearingWatch() {
        return isWearingWatch;
    }

    public void setWearingWatch(boolean wearingWatch) {
        isWearingWatch = wearingWatch;
    }

    public boolean isWearingMp3() {
        return isWearingMp3;
    }

    public void setWearingMp3(boolean wearingMp3) {
        isWearingMp3 = wearingMp3;
    }

    public boolean isHeadbowPurchased() {
        return isHeadbowPurchased;
    }

    public void setHeadbowPurchased(boolean headbowPurchased) {
        isHeadbowPurchased = headbowPurchased;
    }

    public boolean isPinkBowtiePurchased() {
        return isPinkBowtiePurchased;
    }

    public void setPinkBowtiePurchased(boolean pinkBowtiePurchased) {
        isPinkBowtiePurchased = pinkBowtiePurchased;
    }

    public boolean isMp3Purchased() {
        return isMp3Purchased;
    }

    public void setMp3Purchased(boolean mp3Purchased) {
        isMp3Purchased = mp3Purchased;
    }

    public String getChosenAppColorTheme() {
        return chosenAppColorTheme;
    }

    public void setChosenAppColorTheme(String chosenAppColorTheme) {
        this.chosenAppColorTheme = chosenAppColorTheme;
    }

    public boolean isNotificationOn() {
        return isNotificationOn;
    }

    public void setNotificationOn(boolean notificationOn) {
        isNotificationOn = notificationOn;
    }

//    public ArrayList<ChatMessage> getChatHistory() {
//        return chatHistory;
//    }
//
//    public void setChatHistory(ArrayList<ChatMessage> chatHistory) {
//        this.chatHistory = chatHistory;
//    }
}
