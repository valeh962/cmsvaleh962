package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TeachersAll_Interface {

    String BASE_URL = "http://192.168.43.115:8080/requests/";
    @GET("getrequest")
    Call<ArrayList<TeachersAllModel>> getAllTeachers(@Header("Authorization") String authToken
                                                    ,@Query("personType") int personType
                                                    ,@Query("status") int status);

    @GET("getrequest")
    Call<ArrayList<TeachersAllModel>> getNoFilterTeachers(@Header("Authorization") String authToken);

}
