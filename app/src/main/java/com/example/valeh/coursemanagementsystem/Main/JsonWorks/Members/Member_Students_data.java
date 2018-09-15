package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member_Students_data {


    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("grade")
    @Expose
    private Integer grade;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idNumber")
    @Expose
    private String idNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("personTypeId")
    @Expose
    private Integer personTypeId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("univercity")
    @Expose
    private String univercity;
    @SerializedName("otp")
    @Expose
    private String otp;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUnivercity() {
        return univercity;
    }

    public void setUnivercity(String univercity) {
        this.univercity = univercity;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
