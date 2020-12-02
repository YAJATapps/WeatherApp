package com.groupk.weatherapp.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupk.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment{

   public static final String API_KEY = "fea1f728cc8441e7a369047e640c652a";
    private RecyclerView recyclerView;
    private List<Article> articles = new ArrayList<Article>();
    private Adapter adapter;
    private String TAGS = NewsFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_one);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new Adapter(articles,getActivity());
        recyclerView.setAdapter(adapter);
        LoadJson();
    }

    public void LoadJson(){
        AnApiInterface anApiInterface = ApiClient.getApiClient().create(AnApiInterface.class);
      String country = Utils.getCountry();
        Call<Newstext> call;
        call = anApiInterface.getNews(country,API_KEY);
        call.enqueue(new Callback<Newstext>() {
            @Override
            public void onResponse(Call<Newstext> call, Response<Newstext> response) {

                    if (response.isSuccessful() && response.body().getArticle() != null) {

                        if (!articles.isEmpty()) {
                            articles.clear();
                        }
                        articles = response.body().getArticle();

                        adapter = new Adapter(articles, getActivity());
                       adapter.addArticles(articles);

                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        iniListener();
                    }else {

                    Toast.makeText(getActivity(), "No more Response at all!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Newstext> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "No Response, Sorry!", Toast.LENGTH_SHORT).show();
                Log.v("error message",t.getMessage());
                Log.v("calltext",String.valueOf(call));
            }
        });
    }
    private void iniListener(){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getActivity(),NewsDetail.class);

                Article article = articles.get(position);
                i.putExtra("url",article.getUrl());
                i.putExtra("title",article.getTitle());
                i.putExtra("urlToImage",article.getUrlToTmage());
                i.putExtra("date",article.getPublishedAt());
                i.putExtra("author",article.getAuthor());

            startActivity(i);
            }
        });
    }

}