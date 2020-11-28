package com.groupk.weatherapp.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.groupk.weatherapp.R;
import com.groupk.weatherapp.util.CityAdapter;
import com.groupk.weatherapp.util.SharedPrefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectCity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = findViewById(R.id.select_city);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        adapter = new CityAdapter(this, listGroup, listItem);//first para temp
        expandableListView.setAdapter(adapter);
        initListData();

        expandableListView.setOnChildClickListener((parent, v1, groupPosition, childPosition, id) -> {
            Save("CityName", groupPosition, childPosition);
            finish();
            return true;
        });

    }

    public void initListData() {
        listGroup.add(getString(R.string.group1));
        listGroup.add(getString(R.string.group2));
        listGroup.add(getString(R.string.group3));
        listGroup.add(getString(R.string.group4));
        listGroup.add(getString(R.string.group5));

        String[] array;

        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group1);
        for (String item : array) {
            list1.add(item);
        }

        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group2);
        for (String item : array) {
            list2.add(item);
        }

        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group3);
        for (String item : array) {
            list3.add(item);
        }

        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group4);
        for (String item : array) {
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group5);
        for (String item : array) {
            list5.add(item);
        }

        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);
        listItem.put(listGroup.get(3), list4);
        listItem.put(listGroup.get(4), list5);
        adapter.notifyDataSetChanged();
    }

    public String convertToCity(int group, int child) {
        if (group == 0 && child == 0)
            return "Kamloops";
        else if (group == 1 && child == 0)
            return "Toronto";
        else if (group == 2 && child == 0)
            return "Edmonton";
        else if (group == 3 && child == 0)
            return "Winnipeg";
        else if (group == 4 && child == 0)
            return "Quebec";
        else if (group == 5 && child == 0) {
            return "Ottawa";
        }
        return "Kamloops";
    }

    public void Save(String key1, int i1, int i2) {
        SharedPreferences sharedPreferences = SharedPrefs.getPrefs(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        convertToCity(i1, i2);
        editor.putString(key1, convertToCity(i1, i2));
        editor.apply();
    }
}