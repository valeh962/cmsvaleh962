package com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Send_Request {

    String BASE_URL = "http://192.168.43.115:8080/requests/";


    @POST("makerequest")
    Call<ReqBody> send_details(@Body ReqBody reqBody);

}
