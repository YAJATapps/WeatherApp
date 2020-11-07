package com.groupk.weatherapp.ui.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return (i == 0) ? new TodayFragment() : new PredictionFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "Today" : "Prediction";
    }

}
