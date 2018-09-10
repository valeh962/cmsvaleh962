package com.example.valeh.coursemanagementsystem.Main.JsonWorks.GetTokenForUserType;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ITokenUserTypeDataRX {
    String BASE_URL ="http://192.168.43.115:8080/authentication/";
    @GET("auth")
    Observable<LoginResponseData> getType(@Header("authorization") String authToken);
}
