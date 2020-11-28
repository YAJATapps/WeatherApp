package com.groupk.weatherapp.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupk.weatherapp.R;
import com.groupk.weatherapp.util.APIKey;
import com.groupk.weatherapp.util.SharedPrefs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

// Created by Yajat
public class AlertsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alerts, container, false);
        final TextView textView = root.findViewById(R.id.text_alerts);
        textView.setText(R.string.no_alerts);

        new Thread(() -> {
            String cityName = SharedPrefs.getPrefs(getContext()).getString("CityName", "Kamloops");
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + APIKey.getKEY() + "&units =metric";
            try {
                StringBuilder result = new StringBuilder();
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                rd.close();

                Map<String, Object> respMap = jsonToMap(result.toString());
                Map<String, Object> alertsMap = jsonToMap(respMap.get("alerts").toString());

                textView.setText(alertsMap.get("description").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return root;
    }

    private Map<String, Object> jsonToMap(String str) {
        return new Gson().fromJson(str, new
                TypeToken<HashMap<String, Object>>() {
                }.getType());
    }
}