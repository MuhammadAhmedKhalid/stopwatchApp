package com.example.stopwatchapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefDataHelper {
    private static final String  timeListKey = "timeList";

    public void putSharedPref(SharedPreferences sharedPref, String time){
        List<String> timeList = getSharedPref(sharedPref);

        timeList.add(time);

        Gson gson = new Gson();
        String json = gson.toJson(timeList);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(timeListKey,json);
        editor.apply();
    }
    public List<String> getSharedPref(SharedPreferences sharedPref){
        String json = sharedPref.getString(timeListKey, null);
        List<String> timeList = new ArrayList<>();

        if (json == null )
        {
            return timeList;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        timeList = gson.fromJson(json, type);
        return timeList;
    }
}


