package com.example.valeh.coursemanagementsystem.Main.Model.OTPSystem;

public interface IOTPUser {

    String getOTPuserID();
    String getOTPCode();
    int isValidOTP();

}
