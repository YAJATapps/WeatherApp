package com.groupk.weatherapp.ui.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.groupk.weatherapp.SelectCity;

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case(0):
                return new TodayFragment();
            case(1):
                return new PredictionFragment();
            case(2):
                return new SelectCity();
        }
        return new TodayFragment();
        //return (i == 0) ? new TodayFragment() : new PredictionFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case(0):
                return "Today";
            case(1):
                return "Prediction";
            case(2):
                return "Select City";
        }
        return("Today");
        //return position == 0 ? "Today" : "Prediction";
    }

}
