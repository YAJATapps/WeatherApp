package com.groupk.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class Setting extends AppCompatActivity {
    String unit = "Cel";
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void checkButton(View view){
        //radioGroup = findViewById(R.id.radioGroup);
        switch(view.getId()) {
            case R.id.radio_Cel:
                unit = "Celsius";
                Log.v("checkButton", "Celcius");
                break;
            case R.id.radio_Fah:
                unit = "Fahrenheit";
                break;
            case R.id.radio_Kel:
                unit = "Kelvin";
                break;
        }

        /*//refresh fragment
        Fragment temperatureFrag = getFragmentManager().findFragmentById(R.id.today);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(temperatureFrag);
        fragmentTransaction.attach(temperatureFrag);
        fragmentTransaction.commit();*/
    }
}