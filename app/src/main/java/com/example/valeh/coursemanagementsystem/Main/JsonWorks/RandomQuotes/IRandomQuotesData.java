package com.example.valeh.coursemanagementsystem.Main.JsonWorks.RandomQuotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRandomQuotesData {

    @GET("/api/quotes/random/")
    public Call<RandomQuotes> getRandomQuote();
}
