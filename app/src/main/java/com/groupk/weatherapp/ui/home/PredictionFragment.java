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

public class PredictionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prediction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView city = view.findViewById(R.id.city_name);
        city.setText("Kamloops");

        TextView mon = view.findViewById(R.id.weather_monday);
        mon.setText("Mon  " + "-15\u00B0C");

        TextView tue = view.findViewById(R.id.weather_tuesday);
        tue.setText("Tue  " + "-5\u00B0C");

        TextView wed = view.findViewById(R.id.weather_wednesday);
        wed.setText("Wed  " + "-4\u00B0C");

        TextView thur = view.findViewById(R.id.weather_thursday);
        thur.setText("Thur  " + "-2\u00B0C");

        TextView fri = view.findViewById(R.id.weather_friday);
        fri.setText("Fri  " + "-1\u00B0C");

        TextView sat = view.findViewById(R.id.weather_saturday);
        sat.setText("Sat  " + "-23\u00B0C");

        TextView sun = view.findViewById(R.id.weather_sunday);
        sun.setText("Sun  " + "-8\u00B0C");
    }
}