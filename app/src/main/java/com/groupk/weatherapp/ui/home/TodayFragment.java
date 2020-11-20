package com.groupk.weatherapp.ui.home;

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
import com.groupk.weatherapp.util.APIKey;
import com.groupk.weatherapp.util.SharedPrefs;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

// Created by Yajat
public class TodayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView city = view.findViewById(R.id.city_name);
        TextView weather = view.findViewById(R.id.weather);
        TextView temperature = view.findViewById(R.id.temperature);
        TextView wind = view.findViewById(R.id.wind);

        //get unit setting
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        String unit = "C";
        if(radioGroup != null) {
            Log.v("TodayFragment", "Unit=" + unit);
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radio_Cel:
                    unit = "C";
                    break;
                case R.id.radio_Fah:
                    unit = "F";
                    break;
                case R.id.radio_Kel:
                    unit = "K";
                    break;
            }
        }
        String finalUnit = unit;
        Log.v("TodayFragment", "unit="+unit);

        // Load from shared prefs in starting to avoid 2-3 seconds delay in fetching live data.
        city.setText(SharedPrefs.getPrefs(getContext()).getString("city", "Kamloops/CA"));
        weather.setText(SharedPrefs.getPrefs(getContext()).getString("weather", "Snow"));
        temperature.setText(SharedPrefs.getPrefs(getContext()).getString("temperature", "-23 \u00B0C"));
        wind.setText(SharedPrefs.getPrefs(getContext()).getString("wind", "20 KM/H"));

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(APIKey.getKEY());
        helper.setUnits(Units.METRIC);
        helper.getCurrentWeatherByCityName("Kamloops", new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {

                String weatherText = currentWeather.getWeather().get(0).getDescription();
                String temperatureText = currentWeather.getMain().getTempMax() + " \u00B0" + finalUnit;//unit changed to variable
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
}