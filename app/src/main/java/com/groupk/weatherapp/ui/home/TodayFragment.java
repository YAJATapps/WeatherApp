package com.groupk.weatherapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.groupk.weatherapp.R;
import com.groupk.weatherapp.Setting;
import com.groupk.weatherapp.util.APIKey;
import com.groupk.weatherapp.util.SharedPrefs;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

// Created by Yajat
public class TodayFragment extends Fragment {
    String unit;
    String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v("TodayFragment", "onCreateView()");
        unit = getContext().getSharedPreferences("com.groupk.weatherapp.config", Context.MODE_PRIVATE).getString("Unit", "C");
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.v("TodayFragment", "onViewCreated()");
        TextView city = view.findViewById(R.id.city_name);
        TextView weather = view.findViewById(R.id.weather);
        TextView temperature = view.findViewById(R.id.temperature);
        TextView wind = view.findViewById(R.id.wind);

        // Load from shared prefs in starting to avoid 2-3 seconds delay in fetching live data.
        city.setText(SharedPrefs.getPrefs(getContext()).getString("city", "Kamloops/CA"));
        weather.setText(SharedPrefs.getPrefs(getContext()).getString("weather", "Snow"));
        temperature.setText(SharedPrefs.getPrefs(getContext()).getString("temperature", "-23 \u00B0C"));
        wind.setText(SharedPrefs.getPrefs(getContext()).getString("wind", "20 KM/H"));

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(APIKey.getKEY());

        //convert temperature value
        switch(unit){
            case " \u00B0C":
                helper.setUnits(Units.METRIC);
                break;
            case " \u00B0F":
                helper.setUnits(Units.IMPERIAL);
                break;
            case " K":
                break;
        }

        //get city name
        cityName = getContext().getSharedPreferences("com.groupk.weatherapp.config", Context.MODE_PRIVATE).getString("CityName", "Kamloops");
        Log.v("TodayFragment", cityName);

        helper.getCurrentWeatherByCityName(cityName, new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {

                String weatherText = currentWeather.getWeather().get(0).getDescription();
                String temperatureText = currentWeather.getMain().getTempMax() + unit;//unit changed to variable
                String windText = currentWeather.getWind().getSpeed() + " KM/H";

                city.setText(currentWeather.getName() + ", " + currentWeather.getSys().getCountry());
                weather.setText(weatherText);
                temperature.setText(temperatureText);
                wind.setText(windText);

                // Store in shared prefs for cache.
                SharedPrefs.getPrefs(getContext()).edit().putString("weather", weatherText).apply();
                SharedPrefs.getPrefs(getContext()).edit().putString("temperature", temperatureText).apply();
                SharedPrefs.getPrefs(getContext()).edit().putString("wind", windText).apply();
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Error toast.
                Toast.makeText(getContext(), getResources().getString(R.string.weather_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
}