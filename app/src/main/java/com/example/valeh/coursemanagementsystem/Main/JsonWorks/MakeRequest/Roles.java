package com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roles {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public Roles(int i, String s) {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
