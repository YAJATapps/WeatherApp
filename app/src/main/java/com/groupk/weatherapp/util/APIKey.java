package com.groupk.weatherapp.util;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

// Created by Yajat
public class APIKey {

    public static String getKEY() {
        return new String(Base64.decode("YmQwODYwZTJkNDM2ODVhODA2YTgzMzMzZTY4NWQ3YWI=", 0), StandardCharsets.UTF_8);
    }

}