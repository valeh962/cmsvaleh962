package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Subject_Interface {


    String BASE_URL = "http://192.168.43.115:8080/subjects/";
    @GET("getall")
    Call<List<Subject_1>> getResults();



}
