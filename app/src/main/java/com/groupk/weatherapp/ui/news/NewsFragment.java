package com.groupk.weatherapp.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupk.weatherapp.R;

public class NewsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.news_view);
        recyclerView.setAdapter(new NewsAdapter(root.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        return root;
    }
}