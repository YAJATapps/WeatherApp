package com.groupk.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

// Created by Yajat
public class SharedPrefs {

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("weather", Context.MODE_PRIVATE);
    }

}