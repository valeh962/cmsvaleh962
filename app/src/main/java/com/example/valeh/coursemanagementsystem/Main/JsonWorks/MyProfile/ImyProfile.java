package com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ImyProfile {
    String BASE_URl = "http://192.168.43.115:8080/authentication/";
    @GET("info")
    Call<MyProfileData> getUserInfo(@Header("Authorization") String authToken);

}
