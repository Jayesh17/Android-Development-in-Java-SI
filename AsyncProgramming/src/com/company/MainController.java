package com.company;

import java.util.concurrent.CompletableFuture;

public class MainController {

    static SportsNews sports = new SportsNews();
    static BusinessNews business = new BusinessNews();

    public static void getData()
    {
        CompletableFuture<String> futureSportsData = sports.getNewsFromAPI();
        CompletableFuture<String> futureBusinessData = business.getNewsFromAPI();
        String sportsData="";
        String businessData="";
        try
        {
            sportsData = futureSportsData.get();
            businessData = futureBusinessData.get();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        if(sportsData != "" && businessData != "")
        {
            String finalSportsData = sportsData;
            CompletableFuture<Void> parsedSports =
                    CompletableFuture.runAsync(()->{sports.parseAllDataAndStore(finalSportsData);});

            String finalBusinessData = businessData;
            CompletableFuture<Void> parsedBusiness =
                    CompletableFuture.runAsync(()->{business.parseAllDataAndStore(finalBusinessData);});

            try
            {
                parsedSports.get();
                parsedBusiness.get();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        else
        {
            System.out.println("Something Went Wrong");
        }
        //CompletableFuture<>
    }

    public static void showData()
    {
        System.out.println("-------------sports-------------");
        sports.showTitles();
        System.out.println("--------------------------------------");
        System.out.println("--------------business-----------------");
        business.showTitles();
    }
}
