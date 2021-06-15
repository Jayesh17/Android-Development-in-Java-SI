package com.company;

import java.util.List;
import java.util.concurrent.CompletableFuture;

interface ManipulateNews
{
    void addNews(String author, String title, String desc, String URL);
    void addNews(String title, String desc,String URL);
    CompletableFuture<String> getNewsFromAPI();
    void parseAllDataAndStore(String data);
}
public class News {
    private int news_id;
    private String Author;
    private String title;
    private String desc;
    private String URL;

    public News(int id,String author, String title, String desc, String URL) {
        news_id = id;
        Author = author;
        this.title = title;
        this.desc = desc;
        this.URL = URL;
    }

    public News(int id,String author,String title,String URL) {
        news_id = id;
        this.title = title;
        this.desc = "";
        Author = author;
        this.URL=URL;
    }
    public News(int id,String title,String URL) {
        news_id = id;
        this.title = title;
        this.desc = "";
        Author = "";
        this.URL=URL;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getNews_id() {
        return news_id;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getURL() {
        return URL;
    }
}
