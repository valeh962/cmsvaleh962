package com.example.valeh.coursemanagementsystem.Main.JsonWorks.GroupSchedule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGroupSchedule {
    String BASE_URL = "http://192.168.43.115:8080/groups/1/schedule";


    @POST("groups/{groupId}/schedule")
    Call<ArrayList<GroupScheduleData>> getGroupSchedule(@Header("Authorization") String authToken
            , @Query("groupId") int groupId);

}
