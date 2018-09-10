package com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqBody {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("surname")
    @Expose
    String surname;

    @SerializedName("personTypeId")
    @Expose
    int personTypeId;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("subjectId")
    @Expose
    int subjectId;

    @SerializedName("addInfo")
    @Expose
    String addInfo;


    public ReqBody(String name, String surname, int personTypeId, String phone, String email, String address, int subjectId, String addInfo) {
        this.name = name;
        this.surname = surname;
        this.personTypeId = personTypeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subjectId = subjectId;
        this.addInfo = addInfo;
    }
}