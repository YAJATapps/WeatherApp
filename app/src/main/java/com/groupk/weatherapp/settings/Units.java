package com.groupk.weatherapp.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.groupk.weatherapp.R;
import com.groupk.weatherapp.util.SharedPrefs;

public class Units extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the current selected radio option to be selected
        String unit = SharedPrefs.getPrefs(this).getString("Unit", "C");
        int id = -1;
        switch (unit) {
            case "C":
                id = R.id.radio_Cel;
                break;
            case "F":
                id = R.id.radio_Fah;
                break;
            case "K":
                id = R.id.radio_Kel;
                break;
        }
        if (id != -1)
            ((RadioGroup) findViewById(R.id.radioGroup)).check(id);
    }

    public void setUnit(View view) {
        String unit = "C";
        switch (view.getId()) {
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
        Save("Unit", unit);//save to sharedPreference
    }

    public void Save(String key, String s) {
        SharedPreferences sharedPreferences = SharedPrefs.getPrefs(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, s);
        editor.apply();
    }
}