package com.example.valeh.coursemanagementsystem.Main.JsonWorks.OTPRequest;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.Model.OTPSystem.OTPModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IOTPData {

    String BASE_URL = "http://192.168.43.115:8080/authentication/";

    @POST("otpCheck")
    Call<LoginResponseData> sendId(@Body OTPModel bbbo);

}
