package com.groupk.weatherapp.ui.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AnApiInterface {

    @GET("top-headlines")
    Call<Newstext> getNews(

            @Query("country") String country ,
            @Query("apiKey") String apiKey
);

}
