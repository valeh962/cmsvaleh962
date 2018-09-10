package com.example.valeh.coursemanagementsystem.Main.Model.OTPSystem;

import android.text.TextUtils;

public class OTPUser implements IOTPUser {

    String OTPuserId;
    String OTPCode;

    public OTPUser(String OTPuserId, String OTPCode) {
        this.OTPuserId = OTPuserId;
        this.OTPCode = OTPCode;
    }


    @Override
    public String getOTPuserID() {
        return OTPuserId;
    }

    @Override
    public String getOTPCode() {
        return OTPCode;
    }

    @Override
    public int isValidOTP() {

        //1533462840384



        if(getOTPuserID().equals("1234")) {
            return 0;
        }
        else{
            return 1;
        }
    }
}
