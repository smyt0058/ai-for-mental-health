package com.algonquincollege.smyt0058.oso.models;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jason on 2018-03-20.
 *
 * ChatMessage model
 * model for received and sent message with Oso
 *
 */

public class ChatMessage {

    private String              messageContent;
    private String              errorMessage;
    private int                 statusCode;
    private Date                timeStamp;
    private String              timeString;
    private String              accessToken;
    public static final String  MSG_TYPE_RECEIVED = "MSG_TYPE_RECEIVED";
    public static final String  MSG_TYPE_SENT = "MSG_TYPE_SENT";

    private String              msgType;

    public ChatMessage(String messageContent, String errorMessage, int statusCode, Date timeStamp, String accessToken, String msgType ){
        this.messageContent = messageContent;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.accessToken = accessToken;
        this.msgType = msgType;
    }

    public ChatMessage(String messageContent, String msgType, Date timeStamp){
        this.messageContent = messageContent;
        this.msgType = msgType;

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm", Locale.CANADA);
        String time = formatter.format(timeStamp);
        Log.i("timestamp", time);

        this.timeString = time;




    }


    public ChatMessage(String messageContent, String msgType){
        this.messageContent = messageContent;
        this.msgType = msgType;
    }

    public String getMessageContent() {
        return messageContent;
    }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTimeString() {
        return timeString;
    }

    public Date getTimeStamp() { return timeStamp; }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) { this.messageContent = msgType; }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }





}
