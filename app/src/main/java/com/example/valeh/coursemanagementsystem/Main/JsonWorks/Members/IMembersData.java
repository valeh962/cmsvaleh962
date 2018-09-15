package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IMembersData {
    String BASE_URL_STD = "http://192.168.43.115:8080/student/";
    String BASE_URL_TCR = "http://192.168.43.115:8080/teacher/";


    @GET("getStudentList")
    Observable<ArrayList<Member_Students_data>> getMemberStudents(@Header("Authorization") String authToken);

    @GET("getTeacherList")
    Observable<ArrayList<Member_Teachers_data>> getMemberTeachers(@Header("Authorization") String authToken);

    @GET("getTeacherList")
    Call<ArrayList<Member_Teachers_data>> getMemberTeachersn(@Header("Authorization") String authToken);

}
