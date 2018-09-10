package com.example.valeh.coursemanagementsystem.Main.JsonWorks.ChangeStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IChangeStatusData {

    String BASE_URL = "http://192.168.43.115:8080/requests/";

    @POST("setstatus")
    Call<ChangeStatusBody> changeStatus(@Header("Authorization") String authToken
                                        ,@Body ChangeStatusBody body);

}
