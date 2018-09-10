package com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Roles_Interface {
    String BASE_URL = "http://192.168.43.115:8080/roles/";
    @GET("getall")
    Call<List<Roles>> getResultsRoles();
}
