package com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddStudentData {

    @SerializedName("personId")
    @Expose
    private String personId;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("grade")
    @Expose
    private String grade;


    public AddStudentData(String personId, String university, String faculty, String grade) {
        this.personId = personId;
        this.university = university;
        this.faculty = faculty;
        this.grade = grade;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
