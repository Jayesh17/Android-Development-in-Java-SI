package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;

public class BusinessNews implements ManipulateNews {

    //Vector is used because it is thread Safe
    private Vector<News> businessNews = new Vector<>();
    private int count;

    BusinessNews()
    {
        count=0;
    }
    @Override
    public void addNews(String author, String title, String desc, String URL)
    {
        count++;
        News newNews = new News(count,author,title,desc,URL);
        businessNews.add(newNews);
    }
    @Override
    public void addNews(String title, String desc,String URL)
    {
        count++;
        News newNews = new News(count,title,desc,URL);
        businessNews.add(newNews);
    }

    @Override
    public CompletableFuture<String> getNewsFromAPI() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<String> futureTask = new CompletableFuture<>();
        try
        {
            URI uri = URI.create("https://newsapi.org/v2/top-headlines?country=in&category" +
                    "=business" +
                    "&apiKey=6d02c290b6a94d2b9b8e2cc1571070f2");
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
            futureTask = client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
        }
        catch (Exception e)
        {
            System.out.println(e);
            //return futureTask.completeExceptionally(e);
        }
        return futureTask;
    }
    public void parseAllDataAndStore(String data)
    {
        JSONObject mainObject = new JSONObject(data);
        JSONArray articles = mainObject.getJSONArray("articles");
        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i);
            String title = article.getString("title");
            String desc = article.getString("description");
            String URL = article.getString("url");
            Object obj  =  article.get("author");
            if(obj==null)
            {
                addNews(title,desc,URL);
            }
            else
            {
                String author = obj.toString();
                addNews(author,title,desc,URL);
            }
        }
    }

    public void showTitles()
    {
        Iterator<News> it = businessNews.iterator();
        while(it.hasNext())
        {
            News n = it.next();
            System.out.println(n.getNews_id()+" "+n.getTitle());
        }
    }
}
