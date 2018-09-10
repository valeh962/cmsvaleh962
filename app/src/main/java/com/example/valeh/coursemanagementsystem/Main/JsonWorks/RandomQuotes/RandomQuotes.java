package com.example.valeh.coursemanagementsystem.Main.JsonWorks.RandomQuotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomQuotes {

    @SerializedName("quote")
    @Expose
    private String quote;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("cat")
    @Expose
    private String cat;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

}