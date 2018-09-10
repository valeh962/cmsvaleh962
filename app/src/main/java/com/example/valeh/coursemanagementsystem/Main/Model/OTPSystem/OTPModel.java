package com.example.valeh.coursemanagementsystem.Main.Model.OTPSystem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPModel {



    @SerializedName("otp")
    @Expose
    String otp;
    @SerializedName("idNumber")
    @Expose
    String idNumber;


    public OTPModel(String otp, String idNumber) {
        this.otp = otp;
        this.idNumber = idNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
