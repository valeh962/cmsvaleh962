package com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectReq {

    @SerializedName("id")
    @Expose
    int id;

    public SubjectReq(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
