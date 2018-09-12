package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IGroupsData {

    String BASE_URL = "http://192.168.43.115:8080/groups/";
    @GET("getGroupsByPerson")
    Observable<ArrayList<GroupsMainDatum>> getGroupData(@Header("Authorization") String authToken);

}
