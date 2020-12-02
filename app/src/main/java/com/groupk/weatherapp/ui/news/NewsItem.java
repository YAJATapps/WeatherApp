package com.groupk.weatherapp.ui.news;

/**
 * Representing a news item
 */
public class NewsItem {
    public final String title;
    public final String content;
    public final String url;
    public final String imageUrl;

    public NewsItem(String title, String content, String url, String imageUrl) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.imageUrl = imageUrl;
    }
}