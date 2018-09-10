package com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTeacherData {

    @SerializedName("personId")
    @Expose
    private String personId;
    @SerializedName("workPlace")
    @Expose
    private String workPlace;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("experience")
    @Expose
    private String experience;


    public AddTeacherData(String personId, String workPlace, String salary, String experience) {
        this.personId = personId;
        this.workPlace = workPlace;
        this.salary = salary;
        this.experience = experience;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}