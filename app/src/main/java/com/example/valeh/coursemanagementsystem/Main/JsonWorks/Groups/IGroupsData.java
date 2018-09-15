package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAllModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGroupsData {

    String BASE_URL = "http://192.168.43.115:8080/groups/";
    String BASE_URL_ADD = "http://192.168.43.115:8080/student/";

    @POST("addGroup")
    Call<LoginResponseData> createGroup(@Header("Authorization") String authToken,
                                        @Body AddGroup_Data addGroup_data);

    @GET("getGroupsByPerson")
    Observable<ArrayList<GroupsMainDatum>> getGroupData(@Header("Authorization") String authToken);

    @GET("getAllGroups")
    Observable<ArrayList<GroupsMainDatum>> getAllGroupData();

    @POST("addStudentToGroup")
    Call<LoginResponseData> addStdToList(@Header("Authorization") String authToken
            , @Query("personId") int personType
            , @Query("groupId") int status);

}
