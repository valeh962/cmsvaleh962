package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ILoginData {

    String BASE_URL = "http://192.168.43.115:8080/authentication/";

    @POST("login")
    Call<LoginResponseData> sendId(@Body String bbbo);



}
