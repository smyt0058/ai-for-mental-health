package com.algonquincollege.smyt0058.oso.database;

import android.arch.persistence.room.TypeConverter;

import com.algonquincollege.smyt0058.oso.models.ChatMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jason on 2018-04-17.
 */

public class Converters {

    @TypeConverter
    public static ArrayList<ChatMessage> fromString(String value) {
        Type listType = new TypeToken<ArrayList<ChatMessage>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<ChatMessage> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
