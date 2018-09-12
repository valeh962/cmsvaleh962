package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private Object surname;
    @SerializedName("personTypeId")
    @Expose
    private Integer personTypeId;
    @SerializedName("idNumber")
    @Expose
    private Object idNumber;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("otp")
    @Expose
    private Object otp;
    @SerializedName("roleList")
    @Expose
    private Object roleList;
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("workPlace")
    @Expose
    private Object workPlace;
    @SerializedName("experience")
    @Expose
    private Object experience;
    @SerializedName("salary")
    @Expose
    private Integer salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSurname() {
        return surname;
    }

    public void setSurname(Object surname) {
        this.surname = surname;
    }

    public Integer getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

    public Object getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Object idNumber) {
        this.idNumber = idNumber;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

    public Object getRoleList() {
        return roleList;
    }

    public void setRoleList(Object roleList) {
        this.roleList = roleList;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Object getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Object workPlace) {
        this.workPlace = workPlace;
    }

    public Object getExperience() {
        return experience;
    }

    public void setExperience(Object experience) {
        this.experience = experience;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}