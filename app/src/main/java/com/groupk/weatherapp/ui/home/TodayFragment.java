package com.groupk.weatherapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.groupk.weatherapp.R;
import com.groupk.weatherapp.util.APIKey;
import com.groupk.weatherapp.util.SharedPrefs;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

// Created by Yajat
public class TodayFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView city, weather, temperature, wind;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getContext() != null)
            SharedPrefs.getPrefs(getContext()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Context context = view.getContext();
        SharedPrefs.getPrefs(context).registerOnSharedPreferenceChangeListener(this);

        city = view.findViewById(R.id.city_name);
        weather = view.findViewById(R.id.weather);
        temperature = view.findViewById(R.id.temperature);
        wind = view.findViewById(R.id.wind);

        // Load from shared prefs in starting to avoid 2-3 seconds delay in fetching live data.
        city.setText(SharedPrefs.getPrefs(context).getString("city", "Kamloops/CA"));
        weather.setText(SharedPrefs.getPrefs(context).getString("weather", "Snow"));
        temperature.setText(SharedPrefs.getPrefs(context).getString("temperature", "-23 \u00B0C"));
        wind.setText(SharedPrefs.getPrefs(context).getString("wind", "20 KM/H"));
        loadWeatherIcon();

        loadWeather();
    }

    private void loadWeather() {
        if (city == null || weather == null || temperature == null || wind == null || getContext() == null)
            return;

        // Get city name
        String cityName = SharedPrefs.getPrefs(getContext()).getString("CityName", "Kamloops");
        // Get unit
        String unit = SharedPrefs.getPrefs(getContext()).getString("Unit", "C");

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(APIKey.getKEY());
        // Convert temperature value
        switch (unit) {
            case "C":
                helper.setUnits(Units.METRIC);
                break;
            case "F":
                helper.setUnits(Units.IMPERIAL);
                break;
            case "K":
                break;
        }

        helper.getCurrentWeatherByCityName(cityName, new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                String weatherText = currentWeather.getWeather().get(0).getDescription();
                String temperatureText = currentWeather.getMain().getTempMax() + "\u00B0" + unit;//unit changed to variable
                String windText = currentWeather.getWind().getSpeed() + " KM/H";

                city.setText(currentWeather.getName() + ", " + currentWeather.getSys().getCountry());
                weather.setText(weatherText);
                temperature.setText(temperatureText);
                wind.setText(windText);

                if (getContext() != null) {
                    // Store in shared prefs for cache.
                    SharedPrefs.getPrefs(getContext()).edit().putString("weather", weatherText).apply();
                    loadWeatherIcon();
                    SharedPrefs.getPrefs(getContext()).edit().putString("temperature", temperatureText).apply();
                    SharedPrefs.getPrefs(getContext()).edit().putString("wind", windText).apply();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Error toast.
                Toast.makeText(getContext(), getResources().getString(R.string.weather_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("CityName") || key.equals("Unit"))
            loadWeather();
    }

    private void loadWeatherIcon() {
        if (getContext() == null)
            return;
        
        if (weather != null) {
            String weatherText = weather.getText().toString().toLowerCase();
            int iconId = -1;
            if (weatherText.contains("rain")) {
                iconId = R.mipmap.rainy;
            } else if (weatherText.contains("cloud")) {
                iconId = R.mipmap.clouds;
            } else if (weatherText.contains("snow")) {
                iconId = R.mipmap.snow;
            } else {
                weather.setCompoundDrawables(null, null, null, null);
            }
            if (iconId != -1) {
                Drawable drawable = getResources().getDrawable(iconId);
                int size = (int) weather.getTextSize() * 2;
                drawable.setBounds(
                        0,
                        0,
                        size,
                        size
                );
                weather.setCompoundDrawables(drawable, null, null, null);
                weather.setCompoundDrawablePadding(2);
            }
        }
    }

}