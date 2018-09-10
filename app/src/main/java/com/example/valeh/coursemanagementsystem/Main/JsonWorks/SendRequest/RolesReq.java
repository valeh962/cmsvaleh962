package com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RolesReq {

    @SerializedName("id")
    @Expose
    int id;

    public RolesReq(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
