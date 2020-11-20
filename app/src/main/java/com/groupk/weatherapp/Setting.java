package com.groupk.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.groupk.weatherapp.ui.home.TodayFragment;

public class Setting extends AppCompatActivity {
    String unit = "C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setUnit(View view){
        switch(view.getId()) {
            case R.id.radio_Cel:
                unit = " \u00B0C";
                Log.v("setting", "C checked");
                break;
            case R.id.radio_Fah:
                unit = " \u00B0F";
                Log.v("setting", "F checked");
                break;
            case R.id.radio_Kel:
                unit = " K";
                Log.v("setting", "K checked");
                break;
        }
        Save("Unit", unit);//save to sharedPreference
    }

    public String getUnit() {
        return unit;
    }

    public void Save(String key, String s) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.groupk.weatherapp.config",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, s);
        editor.apply();
    }
}