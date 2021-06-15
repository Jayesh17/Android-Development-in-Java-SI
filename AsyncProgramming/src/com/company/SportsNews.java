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

public class SportsNews implements ManipulateNews {
    private Vector<News> sportsNews = new Vector<>();
    int count;
    SportsNews()
    {
        count=0;
    }

    @Override
    public void addNews(String author, String title, String desc, String URL) {
        count++;
        News newNews = new News(count,author,title,desc,URL);
        sportsNews.add(newNews);
    }

    @Override
    public void addNews(String author,String title,String URL) {
        count++;
        News newNews = new News(count,author,title,URL);
        sportsNews.add(newNews);
    }

    public void addNews(String title,String URL) {
        count++;
        News newNews = new News(count,title,URL);
        sportsNews.add(newNews);
    }

    @Override
    public CompletableFuture<String> getNewsFromAPI() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<String> futureTask = new CompletableFuture<>();
        try
        {
            URI url = URI.create("https://newsapi.org/v2/top-headlines?country=in&category=sports" +
                    "&apiKey=6d02c290b6a94d2b9b8e2cc1571070f2");
            HttpRequest request = HttpRequest.newBuilder().uri(url).build();
            futureTask = client.sendAsync(request,HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return futureTask;
    }

    @Override
    public void parseAllDataAndStore(String data) {
        JSONObject mainObject = new JSONObject(data);
        JSONArray articles = mainObject.getJSONArray("articles");
        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i);
            String title = article.getString("title");
            Object descObj = article.get("description");
            String URL = article.getString("url");
            Object authObj  =  article.get("author");
            if(authObj!=null && descObj == null)
            {
                addNews(authObj.toString(),title,URL);
            }
            else if(authObj==null && descObj == null)
            {
                addNews(title,URL);
            }
            else if(authObj!=null && descObj!=null)
            {
                addNews(authObj.toString(),title,descObj.toString(),URL);
            }
        }
    }
    public void showTitles()
    {
        Iterator<News> it = sportsNews.iterator();
        while(it.hasNext())
        {
            News n = it.next();
            System.out.println(n.getNews_id()+" "+n.getTitle());
        }
    }

}
