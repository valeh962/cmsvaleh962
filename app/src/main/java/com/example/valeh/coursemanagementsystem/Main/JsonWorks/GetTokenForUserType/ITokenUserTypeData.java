package com.example.valeh.coursemanagementsystem.Main.JsonWorks.GetTokenForUserType;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ITokenUserTypeData {

    String BASE_URL ="http://192.168.43.115:8080/authentication/";
    @GET("auth")
    Call<LoginResponseData> getType(@Header("authorization") String authToken);

}
