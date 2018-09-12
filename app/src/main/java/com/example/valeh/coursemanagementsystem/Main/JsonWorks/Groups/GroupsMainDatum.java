package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupsMainDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject")
    @Expose
    private Subject subject;
    @SerializedName("teacher")
    @Expose
    private Teacher teacher;
    @SerializedName("studentList")
    @Expose
    private ArrayList<StudentList> studentList = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<StudentList> studentList) {
        this.studentList = studentList;
    }

}