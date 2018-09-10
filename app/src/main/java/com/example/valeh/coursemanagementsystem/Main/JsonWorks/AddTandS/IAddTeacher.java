package com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IAddTeacher {

    String BASE_URL = "http://192.168.43.115:8080/teacher/";
    String BASE_URL1 = "http://192.168.43.115:8080/student/";
    @POST("addTeacher")
    Call<LoginResponseData> addTeacherr(@Header("authorization") String authToken
                                    , @Body AddTeacherData addTeacherData);

    @POST("addStudent")
    Call<LoginResponseData> addStudent(@Header("authorization") String authToken
            ,@Body AddStudentData addTeacherData);
}
