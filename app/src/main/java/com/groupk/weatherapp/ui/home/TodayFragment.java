package com.groupk.weatherapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.groupk.weatherapp.R;

public class TodayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView city = view.findViewById(R.id.city_name);
        city.setText("Kamloops");

        TextView weather = view.findViewById(R.id.weather);
        weather.setText("Snow");

        TextView temperature = view.findViewById(R.id.temperature);
        temperature.setText("-23 \u00B0C");

        TextView wind = view.findViewById(R.id.wind);
        wind.setText("20 KM/H");
    }
}